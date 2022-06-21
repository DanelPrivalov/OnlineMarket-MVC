package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String homePage(Model model) {
        return "greeting";
    }

    @GetMapping("/home")
    public String main(Model model, @AuthenticationPrincipal User user)  {
//        model.addAttribute("name","Добро пожаловать");
        model.addAttribute("user",user);
        return "home";
    }
//
//    @GetMapping("/account/{id}")
//    public String goToAccount (Model model, @PathVariable("id") Long id){
//        model.addAttribute("user", userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
//        return "show-account";
//    }
}

