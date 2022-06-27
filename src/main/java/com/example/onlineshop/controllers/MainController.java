package com.example.onlineshop.controllers;


import com.example.onlineshop.entity.Cart.ProductCart;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.ProductCartRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductCartRepository productCartRepository;//
    private final CartRepository cartRepository;//

    public MainController(UserRepository userRepository, ProductRepository productRepository, ProductCartRepository productCartRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productCartRepository = productCartRepository;//
        this.cartRepository = cartRepository;
    }


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "greeting";
    }

    @GetMapping("/home")
    public String main(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @PostMapping("/home")///
    public String addToCart(@AuthenticationPrincipal User activeUser,
                            @RequestParam("add") Product product) {
        List<ProductCart> productCarts =activeUser.getCart().getProductCarts();
        boolean flag = true;
        for (ProductCart productCart : productCarts) {
            if (productCart.getProduct().getId()==product.getId()){
                productCart.setQuantity(productCart.getQuantity()+1);
                flag = false;
                productCartRepository.save(productCart);
            }
        }
        if(flag){
            ProductCart productCart = new ProductCart();
            productCart.setProduct(product);
            productCart.setQuantity(1);
            activeUser.getCart().AddProductCart(productCart);
            productCartRepository.save(productCart);
            cartRepository.save(activeUser.getCart());
        }
        return "redirect:/home";
    }
}

