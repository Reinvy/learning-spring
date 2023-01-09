package com.learning.learningspring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.learningspring.dto.CategoryDTO;
import com.learning.learningspring.helpers.ResponseHelper;
import com.learning.learningspring.models.entities.Category;
import com.learning.learningspring.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @PostMapping()
    public ResponseEntity<Object> createSupplier(@Validated @RequestBody CategoryDTO body, Errors errors) {
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }

            return ResponseHelper.build(HttpStatus.BAD_REQUEST, "validasi error", listError);
        }

        Category category = Category.builder()
                .name(body.getName())
                .build();

        return categoryService.createCategory(category);
    }

    @PutMapping()
    public ResponseEntity<Object> updateSupplier(@Validated @RequestBody CategoryDTO body, Errors errors) {
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }

            return ResponseHelper.build(HttpStatus.BAD_REQUEST, "validasi error", listError);
        }

        Category category = Category.builder()
                .id(body.getId())
                .name(body.getName())
                .build();

        return categoryService.createCategory(category);
    }
}
