/**
 * 
 */
package com.vk.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vk.ecommerce.dtos.ProductRequestDTO;
import com.vk.ecommerce.models.Category;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.repositories.CategoryRepository;
import com.vk.ecommerce.repositories.ProductRepository;
import com.vk.ecommerce.services.ProductService;

/**
 * Actual Product Service
 */
@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product getProductById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		return productOptional.isPresent() ? productOptional.get() : null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product replaceProduct(Long id, ProductRequestDTO productRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product createProduct(Product product) {
		if(product.getCategory() != null && product.getCategory().getId() == null) {
			Category category = categoryRepository.save(product.getCategory());
			product.setCategory(category);
		}
		return productRepository.save(product);
	}

}
