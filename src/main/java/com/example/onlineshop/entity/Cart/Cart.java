package com.example.onlineshop.entity.Cart;


import com.example.onlineshop.entity.product.Product;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //mappedBy = "order" , cascade = CascadeType.ALL
    @JoinColumn(name = "cart_Id")
    private List<ProductCart> productCarts;

    @Column(name = "sum")
    private Double sum; //sum=0d???

    public Cart() {
    }

    public void AddProductCart(ProductCart productCart) {
        productCarts.add(productCart);
    }
    public void DeleteProductCart(ProductCart productCart_new){
        System.out.println(productCart_new);
        for (ProductCart productCarts : this.productCarts){
            System.out.println(productCarts);
        }
       productCarts.remove(productCart_new);
        for (ProductCart productCarts : this.productCarts){
            System.out.println(productCarts);
        }
         }

//        public ProductCart getProductCart(Long id){
//        return productCarts.stream().filter(productCart -> productCart.getId().equals(id)).findFirst().orElse(null);
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductCart> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCart> productCarts) {
        this.productCarts = productCarts;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
