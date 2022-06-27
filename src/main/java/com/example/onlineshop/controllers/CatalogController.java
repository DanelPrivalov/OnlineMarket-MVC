package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.BoardGameRepository;
import com.example.onlineshop.repository.CollectableFigureRepository;
import com.example.onlineshop.repository.DrinkRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {
    private final DrinkRepository drinkRepository;
    private final BoardGameRepository boardGameRepository;
    private final ProductRepository productRepository;
    private final CollectableFigureRepository collectableFigureRepository;

    public CatalogController(DrinkRepository drinkRepository, BoardGameRepository boardGameRepository, ProductRepository productRepository, CollectableFigureRepository collectableFigureRepository) {
        this.drinkRepository = drinkRepository;
        this.boardGameRepository = boardGameRepository;
        this.productRepository = productRepository;
        this.collectableFigureRepository = collectableFigureRepository;
    }

    @GetMapping("/catalog-boardGames")
    public String boardGameCatalog(@AuthenticationPrincipal User activeUser, Model model){
        model.addAttribute("user", activeUser);
        model.addAttribute("boardGames", boardGameRepository.findAll());
        return "catalog-boardGames";
    }

    @GetMapping("/catalog-drinks")
    public String drinkCatalog(@AuthenticationPrincipal User activeUser, Model model){
       model.addAttribute("user", activeUser);
        model.addAttribute("drinks",drinkRepository.findAll());
        return "catalog-drinks";
    }

    @GetMapping("/catalog-figures")
    public String figuresCatalog(@AuthenticationPrincipal User activeUser,Model model){
        model.addAttribute("user", activeUser);
        model.addAttribute("figures",collectableFigureRepository.findAll());
        return "catalog-figures";
    }

    @GetMapping("/catalog-products")
    public String productsCatalog(@AuthenticationPrincipal User activeUser,Model model){
        model.addAttribute("user", activeUser);
        model.addAttribute("products",productRepository.findAll());
        return "catalog-products";
    }




}
