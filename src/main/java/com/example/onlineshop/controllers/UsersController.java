package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.Cart.Cart;
import com.example.onlineshop.entity.user.ERole;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.CityRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;


@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')") //доступ разрешен только ADMIN
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
        return "users-list";
    }

    @GetMapping("/new")
    public String createUser(Model model, User user) {
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("roles", ERole.values());

        return "create-user";
    }

    @PostMapping("/createuser")
    public String addUser(@RequestParam(name = "roles[]", required = false) String[] roles,@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }
        user.setActive(true);
        user.getRoles().clear();
        if (roles != null) {
            Arrays.stream(roles).forEach(r -> user.getRoles().add(ERole.valueOf(r)));
        }
        Cart cart=new Cart();// нужно ли прописывать это ? чтобы создалась корзина при создании нового пользователя
        if (user.getRoles().contains("CUSTOMER"))
        {user.setCart(cart); }

        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("city", cityRepository.findAll());
        model.addAttribute("roles", ERole.values());
        return "user-update";
    }

    @PostMapping(value = "/update/{id}")
    public String userSave(@RequestParam(name = "roles[]", required = false) String[] roles,
                           @ModelAttribute("user") User user, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user-update";
        }
        user.getRoles().clear();
        if (roles != null) {
            Arrays.stream(roles).forEach(r -> user.getRoles().add(ERole.valueOf(r)));
        }

       // if (user.getRoles().contains("CUSTOMER")||user.getRoles().contains("ADMIN")) //удалить корзину при смене роли??

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