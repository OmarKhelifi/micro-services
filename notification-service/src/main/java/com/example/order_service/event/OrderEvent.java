package com.example.order_service.event;

public class OrderEvent {

        private String eventType;
        private String order;
        private String customerEmail;

        public OrderEvent() {
            super();
        }

        public OrderEvent(String eventType, String order, String customerEmail) {
            this.eventType = eventType;
            this.order = order;
            this.customerEmail = customerEmail;
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


