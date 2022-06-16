package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

//@RequestMapping("/order") для того, чтобы повесить /ордер на все методы
@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private final ProductInOrderRepository productInOrderRepository;
    private final ConditionRepository conditionRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, ProductsRepository productsRepository, ProductInOrderRepository productInOrderRepository, ConditionRepository conditionRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productsRepository = productsRepository;
        this.productInOrderRepository = productInOrderRepository;
        this.conditionRepository = conditionRepository;
    }

    @GetMapping("/order")
    public String findAll(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders"; // список заказов
    }

    @GetMapping("/order-create")   //создание заказа
    public String createOrderForm(Model model) {
        model.addAttribute("conditions", conditionRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productsRepository.findAll());
        model.addAttribute("order", new Order());
        return "order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(Order order) {
        orderRepository.save(order);
        // model.addAttribute("orders", orderRepository.findAll());
        return "redirect:/order";

    }

    @GetMapping("/order-delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId) {
        orderRepository.deleteById(orderId);
        return "redirect:/order";
    }

    @GetMapping("/order-update/{orderId}")
    public String updateOrderForm(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("Invalid type ID" + orderId));;
        model.addAttribute("order", order);
        model.addAttribute("conditions", conditionRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productsRepository.findAll());
        return "order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(Order order, Model model) {
        orderRepository.save(order);
        return "redirect:/order";
    }
}
