/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skipthedishes.skipapi.repository;

import com.skipthedishes.skipapi.entity.OrderEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Vanessa Barcelos
 */
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    public List<OrderEntity> findOrdersByOrderUser(Integer orderUser);
}
