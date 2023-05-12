package com.example.microserviceorder.service;

import com.example.microserviceorder.model.Items;
import com.example.microserviceorder.model.Order;
import com.example.microserviceorder.repository.ItemsRepository;
import com.example.microserviceorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    public List<Items> getAllItems(UUID id) {
        return this.itemsRepository.findByProductId(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
