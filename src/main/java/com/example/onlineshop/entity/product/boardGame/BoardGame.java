package com.example.onlineshop.entity.product.boardGame;

import com.example.onlineshop.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board_games")
public class BoardGame extends Product {

    @Column(name = "board_game_id")
    private Long boardGameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_game_id")
    private BoardGameProducer boardGameProducer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "difficulty_level_id")
    private DifficultyLevel difficultyLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "age_id")
    private Age age;

    @Column(name = "players_num_min")
    private Integer playersNumMin;

    @Column(name = "players_num_max")
    private Integer playersNumMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "play_time_id")
    private PlayTime playTime;

//    @Column(name = "options_id")
//    private Long optionId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "option_boardGame",
            joinColumns = {@JoinColumn(name = "board_game_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")}
    )
    private List<Option> options = new ArrayList<>();

//    public List<Option> getOptions() {
//        return options;
//    }
//
//    public void setOptions(List<Option> options) {
//        this.options = options;
//    }
}
