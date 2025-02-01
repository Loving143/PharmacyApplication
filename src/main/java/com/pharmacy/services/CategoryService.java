package com.pharmacy.services;

import com.pharmacy.Request.AddCategoryRequest;
import com.pharmacy.Request.AddSubCategoryRequest;
import com.pharmacy.entity.Category;

public interface CategoryService {

	void addCategory(AddCategoryRequest request);

	void addSubCategory(AddSubCategoryRequest request) throws Exception;
	void removeCategory (Integer id);

}
