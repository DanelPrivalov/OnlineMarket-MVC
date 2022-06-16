package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.product.drink.DrinkProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkProducerRepository extends JpaRepository<DrinkProducer, Long> {
}
