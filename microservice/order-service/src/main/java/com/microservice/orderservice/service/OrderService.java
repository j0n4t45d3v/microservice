package com.microservice.orderservice.service;

import com.microservice.orderservice.model.Item;
import com.microservice.orderservice.model.Order;

import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrderByUserId(Long userId);
    void addItemsToOrder(Long userId, Item item);

}
