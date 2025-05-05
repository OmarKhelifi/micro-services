package com.example.notification_service.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String orderId;
    private String customerEmail;
    private String message;
    private LocalDateTime sentAt;
    private boolean sentSuccessfully;

    public Notification() {
        super();
    }

    public Notification(String customerEmail, String id, String message, String orderId, LocalDateTime sentAt, boolean sentSuccessfully) {
        this.customerEmail = customerEmail;
        this.id = id;
        this.message = message;
        this.orderId = orderId;
        this.sentAt = sentAt;
        this.sentSuccessfully = sentSuccessfully;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isSentSuccessfully() {
        return sentSuccessfully;
    }

    public void setSentSuccessfully(boolean sentSuccessfully) {
        this.sentSuccessfully = sentSuccessfully;
    }
}
