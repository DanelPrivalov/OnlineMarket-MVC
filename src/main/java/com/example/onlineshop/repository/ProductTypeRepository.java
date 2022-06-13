package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    Optional<Object> findByName(String drink);
}
