package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
