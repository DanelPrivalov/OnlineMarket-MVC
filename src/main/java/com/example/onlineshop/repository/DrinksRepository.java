package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.drink.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends JpaRepository<Drinks, Long> {
}
