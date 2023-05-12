package com.example.microserviceorder.repository;

import com.example.microserviceorder.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

    List<Items> findByProductId(UUID orderId);
}
