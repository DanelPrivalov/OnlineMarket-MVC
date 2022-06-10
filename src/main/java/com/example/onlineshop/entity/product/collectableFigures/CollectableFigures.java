package com.example.onlineshop.entity.product.collectableFigures;

import com.example.onlineshop.entity.product.Products;
import com.example.onlineshop.entity.product.drink.DrinkProducer;
import com.example.onlineshop.entity.product.drink.DrinkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "collectable_figures")

public class CollectableFigures extends Products {

    @Column(name = "collectable_figures_id")
    private Long collectableFiguresId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "collectable_figures_producer_id")
    private CollectableFiguresProducer collectableFiguresProducer;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "fandom_id")
    private Fandom fandom;

    @Column(name = "flexibility")
    private boolean flexibility;
}