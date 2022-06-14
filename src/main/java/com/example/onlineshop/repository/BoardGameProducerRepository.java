package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.boardGame.BoardGameProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardGameProducerRepository extends JpaRepository<BoardGameProducer, Long> {
}
