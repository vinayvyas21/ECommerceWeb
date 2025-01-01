package com.vk.ecommerce.services;

import java.util.List;

import com.vk.ecommerce.models.Product;

public interface ProductService {

	public Product getProductById(Long id);
	
	public List<Product> getAllProducts();
	
	public Product replaceProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void deleteProduct();
}
