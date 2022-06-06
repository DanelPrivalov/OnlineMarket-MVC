package com.example.onlineshop.entity.product.collectableFigures;

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
@Table(name = "fandom")
public class Fandom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fandom_id")
    private Long FandomId;

    @Column(name = "name")
    private String name;
}