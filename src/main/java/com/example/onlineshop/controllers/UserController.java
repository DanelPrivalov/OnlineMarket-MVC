package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping ("/createuser")
//    public String createUser (){
//        return "create-user";
//    }

    @GetMapping ("/createuser")
    public String createUser (@RequestParam("name") String name){
        System.out.println(name);
        return "create-user";
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "users-list";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }

        userRepository.save(user);
        return "redirect:/users-list";
    }
}
