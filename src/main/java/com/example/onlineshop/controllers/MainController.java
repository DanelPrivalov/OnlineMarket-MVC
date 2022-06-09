package com.example.onlineshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(Model model) {
        return "greeting";
    }

    @GetMapping("/home")
    public String main(Model model)  {
        model.addAttribute("name","Добро пожаловать");
        return "home";
    }
}
