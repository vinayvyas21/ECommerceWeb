package com.vk.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vk.ecommerce.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
