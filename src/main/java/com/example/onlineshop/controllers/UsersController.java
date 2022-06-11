package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.user.User;
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

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String showUserList(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

//    @GetMapping ("/{id}")
//    public String showCertainUser (@PathVariable("id") Long id, Model model){
//        return "create-user";
//    }

//    @GetMapping ("/new")
//    public String createUser (@RequestParam(value = "name", required = false) String name, Model model){
//        model.addAttribute("greeting", "Hello," + name);
//        return "create-user";
//    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/createuser")
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-user";
        }
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/users";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);

        return "user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user-update";
        }

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