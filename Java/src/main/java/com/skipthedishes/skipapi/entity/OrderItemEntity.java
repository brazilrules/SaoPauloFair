package com.skipthedishes.skipapi.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItemEntity implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="order_item_id")
    private Integer orderItemId;
    
    @ManyToOne
    @JoinColumn(name="order_item_order", nullable=false)
    private OrderEntity orderItemOrder;
    
    @ManyToOne
    @JoinColumn(name="order_item_product")
    private ProductEntity orderItemProduct;
    
    @Column(name="order_item_quantity")
    private Integer orderItemQuantity;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public OrderEntity getOrderItemOrder() {
        return orderItemOrder;
    }

    public void setOrderItemOrder(OrderEntity orderItemOrder) {
        this.orderItemOrder = orderItemOrder;
    }

    public ProductEntity getOrderItemProducts() {
        return orderItemProduct;
    }

    public void setOrderItemProducts(ProductEntity orderItemProducts) {
        this.orderItemProduct = orderItemProduct;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(Integer orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }
}
