package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.boardGame.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
