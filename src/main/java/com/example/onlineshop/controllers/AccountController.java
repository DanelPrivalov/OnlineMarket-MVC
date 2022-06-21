package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.CityRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public AccountController(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/{id}")
    public String showAccount(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        return "show-account";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("city", cityRepository.findAll());
        return "edit-account";
    }

    @PostMapping("/{id}")
    public String userSave(@ModelAttribute("user") User user, @PathVariable("id") Long id, BindingResult result) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "show-account";
//        }

        userRepository.save(user);
        return "redirect:/account/{id}";
    }

}
