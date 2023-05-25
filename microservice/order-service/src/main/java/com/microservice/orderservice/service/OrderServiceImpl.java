package com.microservice.orderservice.service;

import com.microservice.orderservice.client.ProductClient;
import com.microservice.orderservice.dto.ProductDto;
import com.microservice.orderservice.model.Item;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        List<String> products = productClient.getProduct()
                .stream()
                .map(ProductDto::name)
                .toList();

        if(products.contains(item.getName())){
            Order order = orderRepository.findByUserId(userId)
                    .orElse(Order.builder()
                            .userId(userId)
                            .items(List.of(item))
                            .build());

            List<String> itemsOrder = order.getItems().stream().map(Item::getName).toList();

            if(itemsOrder.contains(item.getName())){
                order.getItems().stream().filter(i -> i.getName().equals(item.getName()))
                        .findFirst()
                        .ifPresent(i -> i.setQuantity(i.getQuantity() + item.getQuantity()));
                log.info("item atualizado");
            }else {
                order.getItems().add(item);
                log.info("item adicionado: {}", item);
            }

            this.orderRepository.save(order);
        }else{
            log.error("Produto não encontrado");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não produto não encontrado");
        }

    }
}
