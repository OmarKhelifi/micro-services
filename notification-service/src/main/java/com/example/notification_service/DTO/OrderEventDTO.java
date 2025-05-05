package com.example.notification_service.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEventDTO {

    private String eventType;
    private String order;
    private String customerEmail;

    public OrderEventDTO() {
        super();
    }

    public OrderEventDTO(String customerEmail, String eventType, String order) {
        this.customerEmail = customerEmail;
        this.eventType = eventType;
        this.order = order;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
