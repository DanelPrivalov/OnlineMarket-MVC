package com.example.onlineshop.entity.product.collectableFigure;

import com.example.onlineshop.entity.product.Product;
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

public class CollectableFigure extends Product {

    @Column(name = "collectable_figures_id")
    private Long collectableFiguresId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "collectable_figures_producer_id")
    private CollectableFigureProducer collectableFigureProducer;

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