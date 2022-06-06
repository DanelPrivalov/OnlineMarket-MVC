package com.example.onlineshop.controllers;
import javax.validation.Valid;
import com.example.onlineshop.entity.product.Products;
import com.example.onlineshop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class ProductsController {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/products")
    public String findAll(Model model){
        List<Products> users = productsRepository.findAll();
        model.addAttribute("users",users);
        return "show-products";
    }

    @GetMapping("/")
    public String homePage(Model model){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showProductsList(Model model) {
        model.addAttribute("products", productsRepository.findAll());
        return "show-products";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Products products) {
        return "add-products";
    }

    @PostMapping("/addproducts")

    public String addProducts(@Valid Products products, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-products";
        }

        productsRepository.save(products);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Products products = productsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("products", products);

        return "update-products";
    }

    @PostMapping("/update/{id}")
    public String updateProducts(@PathVariable("id") long id, @Valid Products products, BindingResult result, Model model) {
        if (result.hasErrors()) {
            products.setId(id);
            return "update-products";
        }

        productsRepository.save(products);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProducts(@PathVariable("id") long id, Model model) {
        Products products = productsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productsRepository.delete(products);

        return "redirect:/index";
    }
}