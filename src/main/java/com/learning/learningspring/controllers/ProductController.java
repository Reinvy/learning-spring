package com.learning.learningspring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;
import com.learning.learningspring.models.entities.Product;
import com.learning.learningspring.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/search")
    public List<Product> searchProduct(@RequestBody Map<String, Object> body) {
        System.out.println(body);
        String name = (String) body.get("name");
        return productService.findByNameProduct(name);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable("id") Long id) {
        try {
            productService.removeOne(id);
            return "Product berhasil dihapus!";
        } catch (Exception e) {
            return "Product gagal dihapus!";
        }
    }
}
