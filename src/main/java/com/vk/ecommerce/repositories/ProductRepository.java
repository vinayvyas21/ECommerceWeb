package com.vk.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vk.ecommerce.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findById(Long id);

	Optional<Product> findByTitleAndDescription(String title, String description);

	List<Product> findTop3ByTitle(String title);

}
