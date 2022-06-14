package com.example.onlineshop.entity.product.boardGame;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "play_time")
public class PlayTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playTimeId;

    @Column(name = "name")
    private String name;

}
