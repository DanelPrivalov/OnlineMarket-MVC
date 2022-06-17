package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.order.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }
    @PostMapping("/Basket")
    public String updateOrder(Order order) {
        return "redirect:/Basket";
    }
    @GetMapping("/home")
    public String main(Model model)  {
        model.addAttribute("name","Добро пожаловать");
        return "home";
    }

}
