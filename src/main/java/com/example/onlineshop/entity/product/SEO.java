package com.example.onlineshop.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SEO_attributes")
public class SEO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEO_attributes_id")
    private Long SEOAttribute;

    @Column(name = "name")
    private String name;

}
