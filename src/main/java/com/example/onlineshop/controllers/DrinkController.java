package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.product.ProductType;
import com.example.onlineshop.entity.product.drink.Drink;
import com.example.onlineshop.entity.product.drink.DrinkType;
import com.example.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DrinkController {
    private final DrinkRepository drinkRepository;
    private final SEORepository seoRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SaleRepository saleRepository;
    private final DrinkProducerRepository drinkProducerRepository;
    private final DrinkTypeRepository drinkTypeRepository;

    @Autowired
    public DrinkController(DrinkRepository drinkRepository, SEORepository seoRepository,
                           ProductTypeRepository productTypeRepository, SaleRepository saleRepository,
                           DrinkProducerRepository drinkProducerRepository,
                           DrinkTypeRepository drinkTypeRepository) {

        this.drinkRepository = drinkRepository;
        this.seoRepository = seoRepository;
        this.productTypeRepository = productTypeRepository;
        this.saleRepository = saleRepository;
        this.drinkProducerRepository = drinkProducerRepository;
        this.drinkTypeRepository = drinkTypeRepository;
    }

    @GetMapping("/drinks")
    public String showDrinks(Model model) {
        model.addAttribute("drinks", drinkRepository.findAll());
        return "show-drinks";
    }

    @GetMapping("/add-drink")
    public String showSignUpForm(Drink drink, Model model) {
        model.addAttribute("SEO", seoRepository.findAll());
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("sales", saleRepository.findAll());
        model.addAttribute("drinkProducers", drinkProducerRepository.findAll());
        model.addAttribute("drinkTypes", drinkTypeRepository.findAll());
        return "add-drink";
    }

    @PostMapping("/add-drink")
    public String addDrinks(@Valid Drink drink, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-drink";
        }
        var seoAttributeId = seoRepository.findById(drink.getSeo().getSEOAttributeId()).orElseThrow();
        drink.setSeo(seoAttributeId);
//        var productTypeId = productTypeRepository.findById(drink.getProductType().getProductTypeId()).orElseThrow();
//        drink.setProductType(productTypeId);
        var productTypeId = productTypeRepository.findByName("Drink").orElseThrow();
        drink.setProductType((ProductType) productTypeId);
        var saleId = saleRepository.findById(drink.getSale().getSaleId()).orElseThrow();
        drink.setSale(saleId);
        var drinkProducerId = drinkProducerRepository.findById(drink.getDrinkProducer().getDrinkProducerId()).orElseThrow();
        drink.setDrinkProducer(drinkProducerId);
        var drinkTypeId = drinkTypeRepository.findById(drink.getDrinkType().getDrinkTypeId()).orElseThrow();
        drink.setDrinkType(drinkTypeId);

        drinkRepository.save(drink);
        return "redirect:/drinks";
    }

    @GetMapping("/delete-drink/{id}")
    public String deleteString(@PathVariable("id") long id, Model model) {
        Drink drink = drinkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid drink Id:" + id));
        drinkRepository.delete(drink);
        return "redirect:/drinks";
    }

    @GetMapping("/edit-drink/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Drink drink = drinkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid drink Id:" + id));
        model.addAttribute("drink", drink);
        model.addAttribute("SEO", seoRepository.findAll());
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("sales", saleRepository.findAll());
        model.addAttribute("drinkProducers", drinkProducerRepository.findAll());
        model.addAttribute("drinkTypes", drinkTypeRepository.findAll());
        return "edit-drink";
    }

    @PostMapping("/edit-drink/{id}")
    public String updateDrink(@PathVariable("id") long id, @Valid Drink drink, BindingResult result, Model model) {
        if (result.hasErrors()) {
            drink.setId(id);
            return "edit-drink";
        }
        var seoAttributeId = seoRepository.findById(drink.getSeo().getSEOAttributeId()).orElseThrow();
        drink.setSeo(seoAttributeId);
        var productTypeId = productTypeRepository.findByName("Drink").orElseThrow();
        drink.setProductType((ProductType) productTypeId);
        var saleId = saleRepository.findById(drink.getSale().getSaleId()).orElseThrow();
        drink.setSale(saleId);
        var drinkProducerId = drinkProducerRepository.findById(drink.getDrinkProducer().getDrinkProducerId()).orElseThrow();
        drink.setDrinkProducer(drinkProducerId);
        var drinkTypeId = drinkTypeRepository.findById(drink.getDrinkType().getDrinkTypeId()).orElseThrow();
        drink.setDrinkType(drinkTypeId);
        drinkRepository.save(drink);

        return "redirect:/drinks";
    }
}
