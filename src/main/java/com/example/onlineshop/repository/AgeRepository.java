package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.boardGame.Age;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeRepository extends JpaRepository<Age,Long> {
}
