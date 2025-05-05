package com.example.payment_service.service;

import com.example.payment_service.entity.Payment;

public interface PaymentService {
    Payment doPayment(Payment payment);
}
