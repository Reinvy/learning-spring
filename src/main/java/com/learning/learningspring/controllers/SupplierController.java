package com.learning.learningspring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.learningspring.dto.SupplierDTO;
import com.learning.learningspring.helpers.ResponseHelper;
import com.learning.learningspring.models.entities.Supplier;
import com.learning.learningspring.services.SupplierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PostMapping()
    public ResponseEntity<Object> createSupplier(@Validated @RequestBody SupplierDTO body, Errors errors) {
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }

            return ResponseHelper.build(HttpStatus.BAD_REQUEST, "validasi error", listError);
        }

        Supplier supplier = Supplier.builder()
                .name(body.getName())
                .address(body.getAddress())
                .email(body.getEmail())
                .build();

        return supplierService.createSupplier(supplier);
    }

    @PutMapping()
    public ResponseEntity<Object> updateSupplier(@Validated @RequestBody SupplierDTO body, Errors errors) {
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }

            return ResponseHelper.build(HttpStatus.BAD_REQUEST, "validasi error", listError);
        }

        Supplier supplier = Supplier.builder()
                .id(body.getId())
                .name(body.getName())
                .address(body.getAddress())
                .email(body.getEmail())
                .build();

        return supplierService.createSupplier(supplier);
    }

}
