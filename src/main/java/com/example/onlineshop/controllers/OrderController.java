package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@RequestMapping("/order") для того, чтобы повесить /ордер на все методы
@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/order")
    public String findAll(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders"; // список заказов
    }

    @GetMapping("/order-create")
    public String createOrderForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("order", new Order());
        return "order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(Order order, Model model, Product product) {
        orderRepository.save(order);
        model.addAttribute("orders", orderRepository.findAll());
        return "redirect:/order";
    }

    @GetMapping("/order-delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId) {
        orderRepository.deleteById(orderId);
        return "redirect:/order";
    }

    @GetMapping("/order-update/{orderId}")
    public String updateOrderForm(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderRepository.getReferenceById(orderId);
        model.addAttribute("order", order);
        return "order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(Order order, Model model) {
        orderRepository.save(order);
        model.addAttribute("orders", orderRepository.findAll());
        return "redirect:/order";
    }
}
