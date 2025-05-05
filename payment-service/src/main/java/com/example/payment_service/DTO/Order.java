package com.example.payment_service.DTO;

public class Order {

    private Long id;
    private String customerId;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private String status;

    public Order() {
        super();
    }

    public Order(String customerId, Long id, Long productId, int quantity, String status, double totalPrice) {
        this.customerId = customerId;
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
