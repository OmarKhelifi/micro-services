package com.example.payment_service.event;


public class PaymentEvent {

    private String eventType;
    private String paymentId;
    private String customerEmail;
    private String orderId;

    public PaymentEvent() {
    }

    public PaymentEvent(String customerEmail, String eventType, String orderId, String paymentId) {
        this.eventType = eventType;
        this.paymentId = paymentId;
        this.customerEmail = customerEmail;
        this.orderId = orderId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
