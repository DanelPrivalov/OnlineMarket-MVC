package com.example.onlineshop.repository;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.product.drink.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Long> {
}
