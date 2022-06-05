package com.example.onlineshop.entity.product.boardGame;


import javax.persistence.*;

@Entity
@Table(name= "play_time")
public class PlayTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playTimeId;

    @Column(name = "name")
    private String name;

}
