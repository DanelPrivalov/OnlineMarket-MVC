package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.boardGame.BoardGame;
import com.example.onlineshop.entity.product.collectableFigure.CollectableFigureProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectableFigureProducerRepository extends JpaRepository<CollectableFigureProducer, Long> {
}
