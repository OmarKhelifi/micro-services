package com.example.payment_service.service;

import com.example.payment_service.DTO.Order;
import com.example.payment_service.Feign.OrderClient;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.event.PaymentEvent;
import com.example.payment_service.exception.PaymentException;
import com.example.payment_service.repository.PaymentRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private static final String TOPIC = "payment-events";

    @Override
    public Payment doPayment(Payment payment) {

        try {
            Order order = orderClient.getOrderById(payment.getOrderId());

            if (order == null) {
                throw new PaymentException("Order not found, cannot process payment");
            }

            if (order.getTotalPrice() > payment.getAmount()) {
                throw new PaymentException("Payment amount is less than order total price");
            }

            payment.setStatus("PAID");

            Payment savedPayment = paymentRepository.save(payment);

            // création de l'événement Kafka
            PaymentEvent event = new PaymentEvent(
                    "PAYMENT_SUCCESS",
                    savedPayment.getId().toString(),
                    savedPayment.getCustomerEmail(),
                    savedPayment.getOrderId().toString()
            );

            // envoi de l'événement
            kafkaTemplate.send(TOPIC, savedPayment.getId().toString(), event);

            return savedPayment;

        } catch (FeignException.NotFound e) {
            // L'order-service a répondu 404
            throw new RuntimeException("Order not found with id: " + payment.getOrderId());
        } catch (FeignException e) {
            // Toute autre erreur Feign (500, 503, etc.)
            throw new RuntimeException("Error contacting Order Service: " + e.getMessage());
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
