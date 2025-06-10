package com.vk.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.ecommerce.models.Category;
import com.vk.ecommerce.services.CategoryService;

/**
 * Category Controller
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * Add a new category
	 * 
	 * @param category The category to add
	 * @return The added category
	 */
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	/**
	 * Get all categories
	 * 
	 * @return List of all categories
	 */
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	/**
	 * Get a category by its ID
	 * 
	 * @param id The ID of the category
	 * @return The category with the specified ID
	 */
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
	
	/**
	 * Delete a category by its ID
	 * 
	 * @param id The ID of the category to delete
	 */
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
}