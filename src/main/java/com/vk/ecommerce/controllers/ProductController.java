package com.vk.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		return this.productService.getProductById(id);
	}
	
	@GetMapping
	public List<Product> getProductById() {
		return new ArrayList<>();
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return new Product();
	}
	
	@PutMapping
	public Product replaceProduct(@RequestBody Product product) {
		return new Product();
	}
	
	@PatchMapping
	public Product updateProduct(@RequestBody Product product) {
		return new Product();
	}
	
	@DeleteMapping("{id}")
	public void updateProduct(@PathVariable("id") long id) {
		
	}
}
