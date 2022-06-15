package com.example.onlineshop.entity.product.drink;

import com.example.onlineshop.entity.product.Products;
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
@Table(name = "Drinks")

public class Drinks extends Products{

    @Column(name = "drink_id")
    private Long drinkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "producer_drink_id")
    private DrinkProducer drinkProducer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "drink_type_id")
    private DrinkType drinkType;
}
