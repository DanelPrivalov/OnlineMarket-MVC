package com.example.onlineshop.controllers;


import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.repository.ChipBasketRepository;
import com.example.onlineshop.repository.ProductInOrderRepository;
import com.example.onlineshop.repository.ProductsRepository;
import com.example.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChipBasketController {

    private final ChipBasketRepository chipBasketRepository;
    private final ProductsRepository productsRepository;
    private final ProductInOrderRepository productInOrderRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ChipBasketController(ChipBasketRepository chipBasketRepository, ProductsRepository productsRepository, ProductInOrderRepository productInOrderRepository, com.example.onlineshop.repository.OrderRepository orderRepository) {
        this.chipBasketRepository = chipBasketRepository;
        this.productsRepository = productsRepository;
        this.productInOrderRepository = productInOrderRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/Basket")
    public String homePage(Model model) {
        List<Order> order = orderRepository.findAll();
        model.addAttribute("order", order);
        return "Basket";
    }

}
