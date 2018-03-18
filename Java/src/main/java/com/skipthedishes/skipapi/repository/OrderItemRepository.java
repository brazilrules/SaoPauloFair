package com.skipthedishes.skipapi.repository;

import com.skipthedishes.skipapi.entity.OrderItemEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
    public List<OrderItemEntity> findOrderItemsByOrderItemOrder(Integer orderItemOrder);
}
