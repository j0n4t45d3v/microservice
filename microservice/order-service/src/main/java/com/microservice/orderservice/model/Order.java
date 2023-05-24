package com.microservice.orderservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("orders")
public class Order {

    private String id;
    private Long userId;
    private List<Item> items;
}
