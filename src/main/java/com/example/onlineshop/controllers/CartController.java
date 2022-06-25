package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.Cart.ProductCart;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductCartRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductCartRepository productCartRepository;

    @Autowired
    public CartController(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository, ProductCartRepository productCartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.productCartRepository = productCartRepository;
    }

    @GetMapping("/cart")
    public String showCart(@AuthenticationPrincipal User activeUser, Model model) {
        model.addAttribute("user", activeUser);
        model.addAttribute("cart", activeUser.getCart());
        model.addAttribute("productCarts", activeUser.getCart().getProductCarts());
        model.addAttribute("products", productRepository.findAll());
        return "show-cart";
    }

    @GetMapping("/productCartDelete/{id}")
    public String deleteProductFromCart(@AuthenticationPrincipal User activeUser, @PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<ProductCart> productCarts =activeUser.getCart().getProductCarts();
        ProductCart deleteProductCart = new ProductCart();
        for (ProductCart productCart : productCarts){
             if (productCart.getProduct().getId()==product.getId()){
                 deleteProductCart = productCart;
            }
        }
        activeUser.getCart().DeleteProductCart(deleteProductCart);
        productCartRepository.delete(deleteProductCart);
        return "redirect:/cart";
    }
}
