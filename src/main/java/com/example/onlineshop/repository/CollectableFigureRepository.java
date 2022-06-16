package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.collectableFigure.CollectableFigure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectableFigureRepository extends JpaRepository<CollectableFigure, Long> {
}
