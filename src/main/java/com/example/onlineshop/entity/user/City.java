package com.example.onlineshop.entity.user;

import lombok.Data;
import org.hibernate.mapping.Set;

import javax.persistence.*;


@Data
@Entity
@Table
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityID;

    @Column(name = "value")
    private String value;

}