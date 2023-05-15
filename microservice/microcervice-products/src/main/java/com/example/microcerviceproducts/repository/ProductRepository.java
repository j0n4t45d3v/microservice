package com.example.microcerviceproducts.repository;

import com.example.microcerviceproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    Optional<Product> findByName(String name);
}
