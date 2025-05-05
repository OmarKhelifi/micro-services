package com.example.order_service.Service;


import com.example.order_service.DTO.Customer;
import com.example.order_service.DTO.Product;
import com.example.order_service.Feign.CustomerClient;
import com.example.order_service.Feign.ProduitClient;
import com.example.order_service.Repository.OrderRepository;
import com.example.order_service.entity.Order;
import com.example.order_service.event.OrderEvent;
import com.example.order_service.exception.OrderException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProduitClient productClient;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final String TOPIC = "order-events";

    public Order addOrder(Order order) {
        try {
            Product p = productClient.getProductById(order.getProductId());
            Customer c = customerClient.getCustomerById(order.getCustomerId());

            if (p == null) {
                throw new OrderException("Produit introuvable avec l'ID : " + order.getProductId());
            }

            if (p.getQuantity() < order.getQuantity()) {
                throw new OrderException("Quantité insuffisante pour le produit : " + p.getName());
            }

            if (!Objects.equals(c.getId(), order.getCustomerId())) {
                throw new OrderException("Client introuvable : " + order.getCustomerId());
            }

            // Sauvegarde de la commande
            Order savedOrder = orderRepository.save(order);

            // Création de l'événement Kafka
            OrderEvent event = new OrderEvent(
                    "ORDER_CREATED",
                    savedOrder.getId().toString(),
                    c.getEmail()
            );

            kafkaTemplate.send(TOPIC, savedOrder.getId().toString(), event);

            return savedOrder;

        } catch (FeignException.NotFound e) {
            throw new OrderException("Produit ou client non trouvé avec l'ID fourni");
        } catch (FeignException e) {
            throw new OrderException("Erreur lors de la communication avec un service externe : " + e.getMessage());
        }
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
