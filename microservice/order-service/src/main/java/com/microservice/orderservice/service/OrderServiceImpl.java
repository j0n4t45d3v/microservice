package com.microservice.orderservice.service;

import com.microservice.orderservice.client.ProductClient;
import com.microservice.orderservice.model.Item;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public Optional<Order> getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void addItemsToOrder(Long userId, Item item) {

        productClient.getProduct();

    }
}
