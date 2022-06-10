package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.collectableFigures.CollectableFigures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectableFiguresRepository extends JpaRepository<CollectableFigures, Long> {
}
