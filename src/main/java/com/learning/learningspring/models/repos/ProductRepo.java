package com.learning.learningspring.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.learningspring.models.entities.Product;
import com.learning.learningspring.models.entities.Supplier;

import jakarta.websocket.server.PathParam;

public interface ProductRepo extends JpaRepository<Product, Long> {
    public List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findByCategoryId(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findBySupplierId(@PathParam("supplier") Supplier supplier);
}
