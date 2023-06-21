package com.microservice.orderservice.service;

import com.microservice.orderservice.dto.RequestRequested;
import com.microservice.orderservice.model.Item;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public Optional<Order> getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void addItemsToOrder(Long userId, Item item) {
        Optional<Order> orderByUser = orderRepository.findByUserId(userId);
        if (orderByUser.isPresent()) {
            orderByUser.get().getItems().add(item);
            orderRepository.save(orderByUser.get());
        } else {
            Order newOrder = new Order();
            newOrder.setUserId(userId);
            newOrder.getItems().add(item);
            orderRepository.save(newOrder);
        }

        RequestRequested order = new RequestRequested("name", item.getQuantity());

        rabbitTemplate.convertAndSend("direct-exchange", "order-queue", order);

    }
}
