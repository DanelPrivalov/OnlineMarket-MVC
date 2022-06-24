package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.Cart.ProductCart;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public MainController(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public String homePage(Model model) {
        return "greeting";
    }

    @GetMapping("/home")
    public String main(Model model, @AuthenticationPrincipal User user)  {
//        model.addAttribute("name","Добро пожаловать");
        model.addAttribute("user",user);
        model.addAttribute("products", productRepository.findAll());
        return "home";
   }

@PostMapping("/home")
    public  String addToCart(@AuthenticationPrincipal User activeUser,
                             @RequestParam("add") Product product){
        ProductCart productCart=new ProductCart();
        productCart.setProduct(product);
        activeUser.getCart().getProductCarts().add(productCart);
        return "redirect:/cart";}

//    @GetMapping("/productCard/{id}")
//    public String goToProductCard(Model model, @PathVariable("id") Long id){
//        model.addAttribute("product", productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid product Id:" + id)));
//        return  "productCard";
//
//    }
//
//    @GetMapping("/account/{id}")
//    public String goToAccount (Model model, @PathVariable("id") Long id){
//        model.addAttribute("user", userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
//        return "show-account";
//    }
}

