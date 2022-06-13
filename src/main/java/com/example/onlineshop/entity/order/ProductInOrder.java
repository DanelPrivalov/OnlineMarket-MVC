package com.example.onlineshop.entity.order;

import com.example.onlineshop.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor

@Getter
@Setter
@Entity
@Table(name="product_in_order")
public class ProductInOrder {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(name = "order_id")
//    private Long orderId;
//    @Column(name = "product_id")
//    private Long productId;

    @Column(name = "final_price")
    private Integer finalPrice;

    @Column(name = "quantity")
    private Integer quantity;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="order_id", insertable = false, updatable = false)
private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

//    public Long getProductId() {
//        return productId;
//    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductInOrder() {
    }
}
