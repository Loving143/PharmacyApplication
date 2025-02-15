package com.pharmacy.services;

import com.pharmacy.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.Request.AddCategoryRequest;
import com.pharmacy.Request.AddSubCategoryRequest;
import com.pharmacy.entity.Category;
import com.pharmacy.entity.Subcategory;
import com.pharmacy.repository.CategoryRepository;
import com.pharmacy.repository.SubCategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private SubCategoryRepository subCategoryRepository;
	@Override
	public void addCategory(AddCategoryRequest request) {
		validateAddCategory(request);
		Category category = new Category(request);
		categoryRepository.save(category);
		
	}

	private void validateAddCategory(AddCategoryRequest request) {
		if(categoryRepository.existsByCode(request.getCode()))
			throw new BadRequestException("Category code already exists!");

		if (categoryRepository.existsByName(request.getName()))
			throw new BadRequestException("Category name already exists!");


	}

	@Override
	public void addSubCategory(AddSubCategoryRequest request) throws Exception {
		Category category = categoryRepository.findById(request.getId())
					.orElseThrow(()->new Exception("Category not found!"));
		Subcategory subcategory = new Subcategory(request);
		subcategory.setCategory(category);
		subCategoryRepository.save(subcategory);
	}

	@Override
	public void removeCategory(Integer id) {
		categoryRepository.deleteById(id);
	}

}
