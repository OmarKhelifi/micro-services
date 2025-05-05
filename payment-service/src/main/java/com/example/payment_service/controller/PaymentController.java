package com.example.payment_service.controller;


import com.example.payment_service.entity.Payment;
import com.example.payment_service.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentServiceImpl.doPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentServiceImpl.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentServiceImpl.getPaymentById(id);
    }


}
