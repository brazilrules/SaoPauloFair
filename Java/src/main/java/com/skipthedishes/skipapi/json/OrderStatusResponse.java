package com.skipthedishes.skipapi.json;

import com.skipthedishes.skipapi.entity.OrderEntity;

public class OrderStatusResponse {
    private Integer orderStatus;
    private String orderStatusDescription;
    private String error;
    
    public OrderStatusResponse(OrderEntity order) {
        orderStatus = order.getOrderStatus();
        orderStatusDescription = order.getOrderStatusDescription();
    }
    
    public OrderStatusResponse(String error) {
        this.error = error;
    }
}
