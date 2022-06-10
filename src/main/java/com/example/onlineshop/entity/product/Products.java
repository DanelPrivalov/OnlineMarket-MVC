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
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "images")
    protected String images;

    @Column(name = "price")
    protected int price;

    @Column(name = "availability")
    protected int availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "SEO_attributes")
    protected SEO seo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "product_type_id")
    protected ProductType productType;

    @Column(name = "rating_id")
    protected int ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "sales")
    protected Sales sales;
}
