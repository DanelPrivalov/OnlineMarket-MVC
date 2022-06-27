package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.Cart.ProductCart;
import com.example.onlineshop.entity.order.Condition;
import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.entity.order.ProductInOrder;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.User;
import com.example.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductCartRepository productCartRepository;
    private final OrderRepository orderRepository;
    private final ProductInOrderRepository productInOrderRepository;
    private final ConditionRepository conditionRepository;

    @Autowired
    public CartController(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository, ProductCartRepository productCartRepository, OrderRepository orderRepository, ProductInOrderRepository productInOrderRepository, ConditionRepository conditionRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.productCartRepository = productCartRepository;
        this.orderRepository = orderRepository;

        this.productInOrderRepository = productInOrderRepository;
        this.conditionRepository = conditionRepository;
    }

    @GetMapping("/cart")
    public String showCart(@AuthenticationPrincipal User activeUser, Model model) {
        model.addAttribute("user", activeUser);
        model.addAttribute("cart", activeUser.getCart());
        model.addAttribute("productCarts", activeUser.getCart().getProductCarts());
        model.addAttribute("products", productRepository.findAll());
        return "show-cart";
    }

    @GetMapping("/productCartDelete/{id}")
    public String deleteProductFromCart(@AuthenticationPrincipal User activeUser, @PathVariable("id") Long id, Model model) {
        //Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
//        List<ProductCart> productCarts = activeUser.getCart().getProductCarts();
//        ProductCart deleteProductCart = new ProductCart();
//        for (ProductCart productCart : productCarts) {
//            if (productCart.getProduct().getId() == product.getId()) {
//                deleteProductCart = productCart;
//            }
//        }
        ProductCart deleteProductCart =  activeUser.getCart().getProductCarts().stream().filter(p->p.getProduct().getId()==id).findFirst().orElseThrow(()-> new IllegalArgumentException("Invalid product Id:" + id));
        activeUser.getCart().DeleteProductCart(deleteProductCart);
        productCartRepository.delete(deleteProductCart);
        return "redirect:/cart";
    }

    @GetMapping("/productCardQuantityDecrease/{id}")
    public String DecreaseQuantityProductFromCart(@AuthenticationPrincipal User activeUser, @PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<ProductCart> productCarts = activeUser.getCart().getProductCarts();
        for (ProductCart productCart : productCarts) {
            if (productCart.getProduct().getId() == product.getId())
                if (productCart.getQuantity() > 1) {
                    productCart.setQuantity(productCart.getQuantity() - 1);
                    productCartRepository.save(productCart);
                }
        }
        return "redirect:/cart";
    }

    @GetMapping("/productCardQuantityIncrease/{id}")
    public String IncreaseQuantityProductFromCart(@AuthenticationPrincipal User activeUser, @PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<ProductCart> productCarts = activeUser.getCart().getProductCarts();
        for (ProductCart productCart : productCarts) {
            if (productCart.getProduct().getId() == product.getId()) {
                productCart.setQuantity(productCart.getQuantity() + 1);
                productCartRepository.save(productCart);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/buy")
    public String BuyProductsFromCart(@AuthenticationPrincipal User activeUser, Model model) {
        //@RequestParam(name = "products") Long id,
//         ProductCart productCart = productCartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<ProductCart> productCarts = activeUser.getCart().getProductCarts();
        Order order = new Order();
        order.setUser(activeUser);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        order.setDate(dateTime.format(formatter));
        order.setCondition(conditionRepository.getReferenceById(1));
        order.setOrderPrice((double)0);
        List<ProductInOrder> productInOrders = new ArrayList<>();
        for (ProductCart oneProductCart : productCarts) {
            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setProduct(oneProductCart.getProduct());
            productInOrder.setQuantity(oneProductCart.getQuantity());
            productInOrder.setFinalPrice((double) oneProductCart.getProduct().getPrice()*oneProductCart.getQuantity()*((100-activeUser.getDiscount())/100));
            productInOrderRepository.save(productInOrder);
            System.out.println(productInOrder);
            productInOrders.add(productInOrder);
            order.setOrderPrice(order.getOrderPrice()+productInOrder.getFinalPrice());
        }
        Iterator<ProductCart> i = productCarts.iterator();
        while (i.hasNext()) {
            ProductCart tempProductCarts = i.next();
            productCartRepository.delete(tempProductCarts);
            i.remove();
        }
        order.setProductInOrder(productInOrders);
        orderRepository.save(order);
        return "redirect:/cart";
        //       return "finalOrder";// сделать вьюшку ваш заказ создан ляляля
    }
}
