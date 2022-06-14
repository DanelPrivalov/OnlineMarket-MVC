package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.boardGame.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
