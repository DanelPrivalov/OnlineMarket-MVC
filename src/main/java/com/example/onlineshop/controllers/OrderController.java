package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.entity.order.ProductInOrder;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductInOrderRepository productInOrderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, ProductInOrderRepository productInOrderRepository) {
        this.orderRepository = orderRepository;
        this.productInOrderRepository = productInOrderRepository;
    }

    @GetMapping("/order")
    public String findAll(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/order-create")
    public String createOrderForm(Order order) {
        return "order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(Order order) {
        orderRepository.save(order);
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
       // ProductInOrder productInOrder=productInOrderRepository.getReferenceById(orderId);
        model.addAttribute("order", order);
    //    model.addAttribute("productInOrder", productInOrder);
        return "order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(Order order) {
        orderRepository.save(order);
        return "redirect:/order";
    }
}
