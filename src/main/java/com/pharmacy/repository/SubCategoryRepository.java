package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.Subcategory;

public interface SubCategoryRepository extends JpaRepository<Subcategory,Integer> {

}
