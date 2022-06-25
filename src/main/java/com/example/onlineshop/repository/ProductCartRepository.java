package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Cart.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
}
