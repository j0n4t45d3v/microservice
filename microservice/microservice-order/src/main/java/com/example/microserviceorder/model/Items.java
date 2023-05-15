package com.example.microserviceorder.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantity;

    private UUID productId;

    @ManyToOne
    private Order order;
}
