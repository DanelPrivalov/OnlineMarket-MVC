package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
