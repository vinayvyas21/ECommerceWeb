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
		return productRepository.findAll();
	}

	@Override
	public Product replaceProduct(Long id, ProductRequestDTO productRequestDTO) {
		Optional<Product> productOptional = productRepository.findById(id);
		Product product = productOptional.get();
		if (product != null) {
			product.setTitle(productRequestDTO.getTitle());
			product.setDescription(productRequestDTO.getDescription());
			product.setPrice(productRequestDTO.getPrice());
			product.setImage(productRequestDTO.getImage());

			return productRepository.save(product);
		}

		return null;
	}

	@Override
	public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) {
		Optional<Product> productOptional = productRepository.findById(id);
		Product product = productOptional.get();
		if (product != null) {
			if (productRequestDTO.getTitle() != null) {
				product.setTitle(productRequestDTO.getTitle());
			}
			if (productRequestDTO.getDescription() != null) {
				product.setDescription(productRequestDTO.getDescription());
			}
			if (productRequestDTO.getPrice() != null) {
				product.setPrice(productRequestDTO.getPrice());
			}
			if (productRequestDTO.getImage() != null) {
				product.setImage(productRequestDTO.getImage());
			}
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
	}

	@Override
	public Product createProduct(Product product) {
		if (product.getCategory() != null && product.getCategory().getId() == null) {
			Category category = categoryRepository.save(product.getCategory());
			product.setCategory(category);
		}
		return productRepository.save(product);
	}

}
