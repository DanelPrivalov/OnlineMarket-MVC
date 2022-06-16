package com.example.onlineshop.repository;

import com.example.onlineshop.entity.order.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
