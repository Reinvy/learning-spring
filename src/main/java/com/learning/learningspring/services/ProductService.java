package com.learning.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.learningspring.models.entities.Product;
import com.learning.learningspring.models.repos.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    public List<Product> findByNameProduct(String name) {
        return productRepo.findByNameContains(name);
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }
}
