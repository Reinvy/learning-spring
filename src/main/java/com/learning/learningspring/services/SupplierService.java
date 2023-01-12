package com.learning.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.learningspring.helpers.ResponseHelper;
import com.learning.learningspring.models.entities.Supplier;
import com.learning.learningspring.models.repos.SupplierRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public ResponseEntity<Object> createSupplier(Supplier supplier) {
        try {
            System.out.println(supplier.getName());
            Supplier data = supplierRepo.save(supplier);
            return ResponseHelper.build(HttpStatus.OK, "Success", data);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> findOne(Long id) {
        try {
            Optional<Supplier> Supplier = supplierRepo.findById(id);
            if (Supplier.isPresent()) {
                return ResponseHelper.build(HttpStatus.OK, "Success", Supplier.get());
            }
            return ResponseHelper.build(HttpStatus.NOT_FOUND, "Data tidak ada!", null);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> findAll() {
        try {
            List<Supplier> categories = supplierRepo.findAll();
            return ResponseHelper.build(HttpStatus.OK, "Success", categories);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public ResponseEntity<Object> removeOne(Long id) {
        try {
            supplierRepo.deleteById(id);
            return ResponseHelper.build(HttpStatus.OK, "Success", null);
        } catch (Exception e) {
            return ResponseHelper.build(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }

    public Supplier findByEmail(String email) {
        Supplier supplier = supplierRepo.findSupplierByEmail(email);
        return supplier;
    }

}
