package com.example.onlineshop.entity.order;

import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.*;


@Entity

@Table(name = "product_in_order")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "final_price")
    private Double finalPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")    //, insertable = false, updatable = false)
    private Product product;


//    public ProductInOrder createProductInOrder(Product product, Integer finalPrice, Integer quantity) {
//       ProductInOrder productInOrder =new ProductInOrder();
//        productInOrder.setProduct(product);
//        productInOrder.setFinalPrice(finalPrice);
//        productInOrder.setQuantity(quantity);
//// подумать над количесвтом остатков при заказе
//        return productInOrder;
//    }



    public ProductInOrder() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", finalPrice=" + finalPrice +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
