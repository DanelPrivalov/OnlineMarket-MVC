package com.example.onlineshop.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Products")
public class Products {
    @Id
    @Column(name = "product_id", unique = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "images")
    private String images;

    @Column(name = "price")
    private int price;

    @Column(name = "availability")
    private int availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "SEO_attributes")
    private SEO seo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "product_type_id")
    private ProductType productType;

    @Column(name = "rating_id")
    private int ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "sales")
    private Sales sales;
}
