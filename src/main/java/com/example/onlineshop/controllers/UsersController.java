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
@RequestMapping ("/users")
public class UsersController {
    private final UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping ()
    public String showUserList (Model model){
        List <User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

//    @GetMapping ("/{id}")
//    public String showCertainUser (@PathVariable("id") Long id, Model model){
//        return "create-user";
//    }

//    @GetMapping ("/createuser")
//    public String createUser (@RequestParam(value = "name", required = false) String name, Model model){
//        model.addAttribute("greeting", "Hello," + name);
//        return "create-user";
//    }


    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "create-user";
    }

    @PostMapping("/createuser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }

        userRepository.save(user);
        return "redirect:/users";
    }
}
