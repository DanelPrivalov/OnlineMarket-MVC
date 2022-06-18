package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.CityRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public UsersController(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping()
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
//        model.addAttribute("cities", cityRepository.findAll());
        return "users-list";
    }


    @GetMapping("/new")
    public String createUser(Model model, User user) {
        model.addAttribute("cities", cityRepository.findAll());
        return "create-user";
    }

    @PostMapping("/createuser")
    public String addUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }

//        var cityID = cityRepository.findById(user.getCity().getCityID()).orElseThrow();
//        user.setCity(cityID);

//        user.setActive(true);
        userRepository.save(user);

        return "redirect:/users";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("city", cityRepository.findAll());

        return "user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user-update";
        }
//        var cityID = cityRepository.findById(user.getCity().getCityID()).orElseThrow();
//        user.setCity(cityID);

        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        return "redirect:/users";
    }

}