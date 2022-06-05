package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
