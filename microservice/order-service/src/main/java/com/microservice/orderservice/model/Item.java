package com.microservice.orderservice.model;

import org.springframework.data.mongodb.core.mapping.Document;


public class Item {
    private String name;
    private Integer quantity;
    private Double price;

}
