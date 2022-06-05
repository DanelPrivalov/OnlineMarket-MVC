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

    @ManyToOne(optional = false)
    private SEO seo;

    @Column(name = "SEO_attributes")
    private String SEOAttributes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "product_type_id")
    private ProductType productType;

    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "sale_id")
    private int saleId;

}
