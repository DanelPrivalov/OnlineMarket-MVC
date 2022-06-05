package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.drink.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinksRepository extends JpaRepository<Drinks, Long> {
}
