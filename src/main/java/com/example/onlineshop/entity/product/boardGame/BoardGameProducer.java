package com.example.onlineshop.entity.product.boardGame;

import com.example.onlineshop.entity.product.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class BoardGameProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer boardGameProducerId;

    @Column(name="name")
    private String name;

    @Column(name="logo")
    private String logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;


}
