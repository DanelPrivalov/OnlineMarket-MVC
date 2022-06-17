package com.example.onlineshop.controllers;
import javax.validation.Valid;

import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.repository.BoardGameRepository;
import com.example.onlineshop.repository.CollectableFigureRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class ProductController {
    private final ProductRepository productRepository;
    private final BoardGameRepository boardGameRepository;
    private final CollectableFigureRepository collectableFigureRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, BoardGameRepository boardGameRepository, CollectableFigureRepository collectableFigureRepository) {
        this.productRepository = productRepository;
        this.boardGameRepository = boardGameRepository;
        this.collectableFigureRepository = collectableFigureRepository;
    }

    @GetMapping("/products")
    public String findAll(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "show-products";
    }

//    @GetMapping("/")
//    public String homePage(Model model){
//        return "redirect:/index";
//    }

    @GetMapping("/index")
    public String showProductsList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "show-products";
    }

    @GetMapping("/map")
    public String showMap(Model model) {
        return "map";
    }

//    @GetMapping("/games")
//    public String showGames(Model model) {
//        model.addAttribute("games", boardGameRepository.findAll());
//        return "show-games";
//    }

//    @GetMapping("/figures")
//    public String showFigures(Model model) {
//        model.addAttribute("figures", collectableFigureRepository.findAll());
//        return "show-figures";
//    }

//    @GetMapping("/signup")
//    public String showSignUpForm(Product product) {
//        return "add-products";
//    }
//
//    @PostMapping("/addproducts")
//    public String addProducts(@Valid Product product, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-products";
//        }
//        productRepository.save(product);
//        return "redirect:/index";
//    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
//        model.addAttribute("products", product);
//
//        return "update-products";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateProducts(@PathVariable("id") long id, @Valid Product product, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            product.setId(id);
//            return "update-products";
//        }
//        productRepository.save(product);
//
//        return "redirect:/index";
//    }

    @GetMapping("/delete/{id}")
    public String deleteProducts(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productRepository.delete(product);

        return "redirect:/index";
    }
}