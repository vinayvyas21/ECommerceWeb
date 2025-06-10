package com.vk.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.ecommerce.dtos.ProductRequestDTO;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.services.ProductService;

/**
 * Product Controller
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;

	ProductController(@Qualifier("selfProductService") ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Get product by id
	 * 
	 * @param id
	 * @return Product
	 */
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		return this.productService.getProductById(id);
	}

	/**
	 * Get all products
	 * 
	 * @return List<Product>
	 */
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Create a new product
	 * 
	 * @param product
	 * @return Product
	 */
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	/**
	 * Replace a product
	 * 
	 * @param id
	 * @param productRequestDTO
	 * @return Product
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id,
			@RequestBody ProductRequestDTO productRequestDTO) {
		Product product = productService.replaceProduct(id, productRequestDTO);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	/**
	 * Update a product
	 * 
	 * @param id
	 * @param productRequestDTO
	 * @return Product
	 */
	@PatchMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
			@RequestBody ProductRequestDTO productRequestDTO) {
		Product product = productService.updateProduct(id, productRequestDTO);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	/**
	 * Delete a product
	 * 
	 * @param id
	 */
	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable("id") long id) {
		productService.deleteProduct(id);
	}
}
