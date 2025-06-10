package com.vk.ecommerce.services;

import java.util.List;

import com.vk.ecommerce.models.Category;

public interface CategoryService {
	Category addCategory(Category category);

	List<Category> getAllCategories();

	Category getCategoryById(Long id);

	void deleteCategory(Long id);
}
