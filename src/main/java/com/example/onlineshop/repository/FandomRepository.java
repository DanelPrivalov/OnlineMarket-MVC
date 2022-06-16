package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.collectableFigure.Fandom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FandomRepository extends JpaRepository<Fandom, Long> {
}
