package com.example.onlineshop.entity.product.collectableFigures;

import com.example.onlineshop.entity.product.Country;
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
@Table(name = "collectable_figures_producer")
public class CollectableFiguresProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "collectable_figures_producer_id")
    private Long collectableFiguresProducerId;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "country")
    private Country country;
}