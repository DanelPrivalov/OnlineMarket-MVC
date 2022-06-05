package com.example.onlineshop.entity.product.boardGame;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer optionId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "options")
       List<BoardGame> boardGames = new ArrayList<>();
}
