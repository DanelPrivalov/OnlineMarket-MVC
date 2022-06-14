package com.example.onlineshop.repository;

import com.example.onlineshop.entity.chipBasketAndFavorite.ChipBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChipBasketRepository  extends JpaRepository<ChipBasket, Long> {

}
