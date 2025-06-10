package com.vk.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vk.ecommerce.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	// 1. Find all top-level categories (no parent category)
	List<Category> findByParentCategoryIsNull();

	// 2. Find all subcategories of a given category
	List<Category> findByParentCategoryCategoryId(Long parentCategoryId);

	// 3. Check if category name already exists
	boolean existsByCategoryName(String categoryName);

	// 4. Count subcategories of a given parent category
	@Query("SELECT COUNT(c) FROM Category c WHERE c.parentCategory.categoryId = :parentId")
	long countSubcategoriesByParentId(Long parentId);

}
