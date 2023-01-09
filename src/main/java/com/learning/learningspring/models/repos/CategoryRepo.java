package com.learning.learningspring.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.learningspring.models.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
