package com.example.onlineshop.repository;
import com.example.onlineshop.entity.product.boardGame.PlayTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayTimeRepository extends JpaRepository<PlayTime, Long> {
}
