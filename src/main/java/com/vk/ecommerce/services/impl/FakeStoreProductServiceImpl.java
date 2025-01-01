package com.vk.ecommerce.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vk.ecommerce.converters.ProductConverter;
import com.vk.ecommerce.dtos.FakeStoreProductDTO;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.services.ProductService;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

	private RestTemplate restTemplate;

	public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Product getProductById(Long id) {
		FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
				FakeStoreProductDTO.class);
		return ProductConverter.convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product replaceProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct() {
		// TODO Auto-generated method stub
		
	}

}
