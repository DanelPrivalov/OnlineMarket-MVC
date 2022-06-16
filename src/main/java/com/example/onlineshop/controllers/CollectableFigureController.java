package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.product.ProductType;
import com.example.onlineshop.entity.product.collectableFigure.CollectableFigure;
import com.example.onlineshop.entity.product.drink.Drink;
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
public class CollectableFigureController {
    private final CollectableFigureRepository collectableFigureRepository;
    private final SEORepository seoRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SaleRepository saleRepository;
    private final CollectableFigureProducerRepository collectableFigureProducerRepository;
    private final FandomRepository fandomRepository;

    @Autowired
    public CollectableFigureController(CollectableFigureRepository collectableFigureRepository, SEORepository seoRepository,
                                       ProductTypeRepository productTypeRepository, SaleRepository saleRepository,
                                       CollectableFigureProducerRepository collectableFigureProducerRepository,
                                       FandomRepository fandomRepository) {

        this.collectableFigureRepository = collectableFigureRepository;
        this.seoRepository = seoRepository;
        this.productTypeRepository = productTypeRepository;
        this.saleRepository = saleRepository;
        this.collectableFigureProducerRepository = collectableFigureProducerRepository;
        this.fandomRepository = fandomRepository;
    }

    @GetMapping("/figures")
    public String showFigures(Model model) {
        model.addAttribute("figures", collectableFigureRepository.findAll());
        return "show-figures";
    }
    @GetMapping("/add-figure")
    public String showSignUpForm(CollectableFigure collectableFigure, Model model) {
        model.addAttribute("SEO", seoRepository.findAll());
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("sales", saleRepository.findAll());
        model.addAttribute("figureProducers", collectableFigureProducerRepository.findAll());
        model.addAttribute("fandoms", fandomRepository.findAll());
        return "add-figure";
    }

    @PostMapping("/add-figure")
    public String addFigure(@Valid CollectableFigure collectableFigure, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-figure";
        }
        var seoAttributeId = seoRepository.findById(collectableFigure.getSeo().getSEOAttributeId()).orElseThrow();
        collectableFigure.setSeo(seoAttributeId);
        var productTypeId = productTypeRepository.findByName("Figure").orElseThrow();
        collectableFigure.setProductType((ProductType) productTypeId);
        var saleId = saleRepository.findById(collectableFigure.getSale().getSaleId()).orElseThrow();
        collectableFigure.setSale(saleId);
        var figureProducerId = collectableFigureProducerRepository.findById(collectableFigure.getCollectableFigureProducer().getCollectableFiguresProducerId()).orElseThrow();
        collectableFigure.setCollectableFigureProducer(figureProducerId);
        var fandomId = fandomRepository.findById(collectableFigure.getFandom().getFandomId()).orElseThrow();
        collectableFigure.setFandom(fandomId);

        collectableFigureRepository.save(collectableFigure);
        return "redirect:/figures";
    }

    @GetMapping("/edit-figure/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        CollectableFigure collectableFigure = collectableFigureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid figure Id:" + id));
        model.addAttribute("figure", collectableFigure);
        model.addAttribute("SEO", seoRepository.findAll());
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("sales", saleRepository.findAll());
        model.addAttribute("figureProducers", collectableFigureProducerRepository.findAll());
        model.addAttribute("fandoms", fandomRepository.findAll());
        return "edit-figure";
    }

    @PostMapping("/edit-figure/{id}")
    public String updateFigure(@PathVariable("id") long id, @Valid CollectableFigure collectableFigure, BindingResult result, Model model) {
        if (result.hasErrors()) {
            collectableFigure.setId(id);
            return "edit-figure";
        }
        var seoAttributeId = seoRepository.findById(collectableFigure.getSeo().getSEOAttributeId()).orElseThrow();
        collectableFigure.setSeo(seoAttributeId);
        var productTypeId = productTypeRepository.findByName("Figure").orElseThrow();
        collectableFigure.setProductType((ProductType) productTypeId);
        var saleId = saleRepository.findById(collectableFigure.getSale().getSaleId()).orElseThrow();
        collectableFigure.setSale(saleId);
        var figureProducerId = collectableFigureProducerRepository.findById(collectableFigure.getCollectableFigureProducer().getCollectableFiguresProducerId()).orElseThrow();
        collectableFigure.setCollectableFigureProducer(figureProducerId);
        var fandomId = fandomRepository.findById(collectableFigure.getFandom().getFandomId()).orElseThrow();
        collectableFigure.setFandom(fandomId);

        collectableFigureRepository.save(collectableFigure);
        return "redirect:/figures";
    }

    @GetMapping("/delete-figure/{id}")
    public String deleteString(@PathVariable("id") long id, Model model) {
        CollectableFigure collectableFigure = collectableFigureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid figure Id:" + id));
        collectableFigureRepository.delete(collectableFigure);

        return "redirect:/figures";
    }
}
