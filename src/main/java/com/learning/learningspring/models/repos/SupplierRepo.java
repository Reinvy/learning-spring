package com.learning.learningspring.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.learningspring.models.entities.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

    public Supplier findSupplierByEmail(String email);

}
