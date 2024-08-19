package com.blog.service;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryService {

//	CREATE
	CategoryDto createCategory(CategoryDto categoryDto);

//	UPDATE
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

//	 DELETE
	void deleteCategory(Integer categoryId);

//	GET
	CategoryDto getSingleCategory(Integer categoryId);

//	GET A::
	List<CategoryDto> getAllCategory();
}
