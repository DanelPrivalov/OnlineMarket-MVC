package com.example.onlineshop.repository;

import com.example.onlineshop.entity.order.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}
