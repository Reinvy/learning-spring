package com.learning.learningspring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.learningspring.helpers.ResponseHelper;
import com.learning.learningspring.models.entities.Product;
import com.learning.learningspring.models.entities.Supplier;
import com.learning.learningspring.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@Validated @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }

            return ResponseHelper.build(HttpStatus.BAD_REQUEST, "validasi error", listError);
        }
        return productService.createProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@Validated @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }

            return ResponseHelper.build(HttpStatus.BAD_REQUEST, "validasi error", listError);
        }
        return productService.createProduct(product);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchProduct(@RequestBody Map<String, Object> body) {
        System.out.println(body);
        String name = (String) body.get("name");
        return productService.findByNameProduct(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable("id") Long id) {
        return productService.removeOne(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> addSupplier(@RequestBody Supplier body, @PathVariable("id") Long productId) {
        return productService.addSupplier(body, productId);
    }
}
