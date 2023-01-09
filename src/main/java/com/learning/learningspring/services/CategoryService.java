package com.learning.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.learningspring.helpers.ResponseHelper;
import com.learning.learningspring.models.entities.Category;
import com.learning.learningspring.models.repos.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<Object> createCategory(Category category) {
        try {
            final Category data = categoryRepo.save(category);
            return ResponseHelper.build(HttpStatus.OK, "Success", data);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> findOne(Long id) {
        try {
            Optional<Category> category = categoryRepo.findById(id);
            if (category.isPresent()) {
                return ResponseHelper.build(HttpStatus.OK, "Success", category.get());
            }
            return ResponseHelper.build(HttpStatus.NOT_FOUND, "Data tidak ada!", null);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> findAll() {
        try {
            List<Category> categories = categoryRepo.findAll();
            return ResponseHelper.build(HttpStatus.OK, "Success", categories);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> removeOne(Long id) {
        try {
            categoryRepo.deleteById(id);
            return ResponseHelper.build(HttpStatus.OK, "Success", null);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }
}
