package com.example.microserviceorder.repository;

import com.example.microserviceorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{


    List<Order> findAllByCustomerId(UUID customerId);
}
