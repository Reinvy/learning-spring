package com.learning.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.learningspring.helpers.ResponseHelper;
import com.learning.learningspring.models.entities.Product;
import com.learning.learningspring.models.repos.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<Object> createProduct(Product product) {
        try {
            Product savedProduct = productRepo.save(product);
            return ResponseHelper.build(HttpStatus.OK, "Success", savedProduct);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }

    }

    public ResponseEntity<Object> findOne(Long id) {
        try {
            Optional<Product> product = productRepo.findById(id);
            if (product.isPresent()) {
                return ResponseHelper.build(HttpStatus.OK, "Success", product.get());
            }
            return ResponseHelper.build(HttpStatus.NOT_FOUND, "Data tidak ada", null);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }

    }

    public ResponseEntity<Object> findByNameProduct(String name) {
        try {
            List<Product> products = productRepo.findByNameContains(name);
            return ResponseHelper.build(HttpStatus.OK, "Success", products);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }

    }

    public ResponseEntity<Object> findAll() {
        try {
            List<Product> products = productRepo.findAll();
            return ResponseHelper.build(HttpStatus.OK, "Success", products);

        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> removeOne(Long id) {
        try {
            productRepo.deleteById(id);
            return ResponseHelper.build(HttpStatus.OK, "Success", null);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }
}
