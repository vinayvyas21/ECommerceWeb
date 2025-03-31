package com.vk.ecommerce.services;

import java.util.List;

import com.vk.ecommerce.dtos.ProductRequestDTO;
import com.vk.ecommerce.models.Product;

public interface ProductService {

	public Product getProductById(Long id);
	
	public List<Product> getAllProducts();
	
	public Product replaceProduct(Long id, ProductRequestDTO productRequestDTO);
	
	public Product updateProduct(Long id, ProductRequestDTO productRequestDTO);
	
	public void deleteProduct(Long id);
	
	public Product createProduct(Product product);
}
