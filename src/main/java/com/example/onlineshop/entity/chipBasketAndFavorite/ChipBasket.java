package com.example.onlineshop.entity.chipBasketAndFavorite;

import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chip_basket")
public class ChipBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chip_basket_id")
    private Long ChipBasketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "comment")
    private String Commentription;
}
