package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.SEO;
import com.example.onlineshop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SEORepository extends JpaRepository<SEO, Long> {
}
