package com.example.onlineshop.entity.product.boardGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board_games")
public class BoardGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer boardGameId;
    @Getter
    @Setter
    @Column(name = "product_id")
    private Integer productId;
    @Getter
    @Setter
    @Column(name = "producer_game_id")
    private Integer producerGameId;
    @Getter
    @Setter
    @Column(name = "difficulty_level")
    private  Integer difficultyLevelId;
    @Getter
    @Setter
    @Column(name = "genre_id")
    private  Integer genreId;
    @Getter
    @Setter
    @Column(name = "age_id")
    private Integer ageId;
    @Getter
    @Setter
    @Column(name = "players_num_min")
    private Integer playersNumMin;
    @Getter
    @Setter
    @Column(name = "players_num_max")
    private Integer getPlayersNumMax;
    @Getter
    @Setter
    @Column(name = "play_time_id")
    private  Integer playTimeId;
    @Getter
    @Setter
    @Column(name = "options_id")
    private Integer optionId;


}
