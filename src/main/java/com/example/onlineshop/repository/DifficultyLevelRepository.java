package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.boardGame.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel,Long> {
}
