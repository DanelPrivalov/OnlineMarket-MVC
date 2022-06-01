package com.example.onlineshop.entity.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//import java.sql.Date;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="date")
    private LocalDate date; //разобраться с Date LocalDate и тд

    @Column(name="condition_id")
    private Integer conditionId;

    @Column(name="comment")
    private String comment;

    public Order(Integer orderId) {
        this.orderId = orderId;
    }
}

