package com.microservice.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("orders")
public class Order {
    private String id;
    private Long userId;
    private List<Item> items;
}
