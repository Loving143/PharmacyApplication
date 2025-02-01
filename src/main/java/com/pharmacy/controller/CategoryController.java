package com.pharmacy.controller;

import com.pharmacy.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pharmacy.Request.AddCategoryRequest;
import com.pharmacy.Request.AddSubCategoryRequest;
import com.pharmacy.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public String addCategory(@RequestBody AddCategoryRequest request) {
		categoryService.addCategory(request);
		return "category added successfully";
	}
	
	@PostMapping("/add/subcategory")
	public String addSubCategoryCategory(@RequestBody AddSubCategoryRequest request) throws Exception {
		categoryService.addSubCategory(request);
		return "subcategory added successfully";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory (@PathVariable Integer id) {
		categoryService.removeCategory(id);
		return  ResponseEntity.ok().body("Category Deleted Successfully.");
	}

}
