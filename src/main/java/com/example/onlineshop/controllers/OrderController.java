package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.entity.order.ProductInOrder;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//@RequestMapping("/order") для того, чтобы повесить /ордер на все методы
@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ConditionRepository conditionRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, ConditionRepository conditionRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
        this.productRepository = productRepository;
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
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("order", new Order());

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
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("Invalid type ID" + orderId));
        model.addAttribute("order", order);
        model.addAttribute("conditions", conditionRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(@Valid Order order, BindingResult bindingResult, Model model, Product product, Integer quantity, Integer finalPrice) {
        orderRepository.save(order);
        return "redirect:/order";
    }

}
