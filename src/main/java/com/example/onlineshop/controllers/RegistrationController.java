package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.Cart.Cart;
import com.example.onlineshop.entity.user.ERole;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.CityRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/registration")
    public String registration(User user, Model model) {
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByLogin(user.getLogin());

        //List<User> users=userRepository.findAll();
        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(ERole.CUSTOMER));
        user.setCity(cityRepository.getReferenceById(1));//
        userRepository.save(user);
        Cart cart = new Cart();// нужно ли прописывать это ? чтобы создалась корзина при создании нового пользователя
        user.setCart(cart);
        return "redirect:/login";

    }
}
