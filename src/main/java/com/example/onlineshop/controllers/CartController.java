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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showCart (@AuthenticationPrincipal User activeUser, Model model){
    model.addAttribute("user", activeUser);
    model.addAttribute("cart", activeUser.getCart());
    model.addAttribute("productCarts", activeUser.getCart().getProductCarts());
    model.addAttribute("products", productRepository.findAll());
       return "show-cart";
    }
//    @PostMapping ("/cart")
//    public  String addToCart(@AuthenticationPrincipal User activeUser,
//                             @RequestParam ("add")Product product){
//    ProductCart productCart=new ProductCart();
//    productCart.setProduct(product);
//    productCart.setQuantity(1);
//    productCartRepository.save(productCart);
////    activeUser.getCart().getProductCarts().add(productCart);
////    cartRepository.save(activeUser.getCart());
//    return "redirect:/home";
//    }
}
