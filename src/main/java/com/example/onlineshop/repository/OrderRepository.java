package com.example.onlineshop.repository;

import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByUser(User user);
}
