package com.learning.learningspring.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.learningspring.models.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String name);
}
