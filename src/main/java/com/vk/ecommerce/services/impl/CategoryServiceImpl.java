package com.vk.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.ecommerce.models.Category;
import com.vk.ecommerce.repositories.CategoryRepository;
import com.vk.ecommerce.services.CategoryService;

/**
 * Category Service Implementation
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}