package com.vk.ecommerce.services.impl;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.vk.ecommerce.converters.ProductConverter;
import com.vk.ecommerce.dtos.FakeStoreProductDTO;
import com.vk.ecommerce.dtos.ProductRequestDTO;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.services.ProductService;

/**
 * Fake Store Product Service Implementation
 * 
 * @author vk
 *
 */
@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {

	private RestTemplate restTemplate;
	private RedisTemplate<String, Object> redisTemplate;

	public FakeStoreProductServiceImpl(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
		this.restTemplate = restTemplate;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Product getProductById(Long id) {
		Product product = (Product) this.redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
		if (product != null) {
			return product;
		}
		FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
				FakeStoreProductDTO.class);
		product = ProductConverter.convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
		this.redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, product);
		return product;
//		return ProductConverter.convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
	}

	@Override
	public List<Product> getAllProducts() {
		FakeStoreProductDTO[] fakeStoreProductDTOList = restTemplate.getForObject("https://fakestoreapi.com/products",
				FakeStoreProductDTO[].class);
		return ProductConverter.convertFakeStoreProductsListToProductList(fakeStoreProductDTOList);
	}

	@Override
	public Product replaceProduct(Long id, ProductRequestDTO productRequestDTO) {
		RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDTO, FakeStoreProductDTO.class);
		HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(
				FakeStoreProductDTO.class, restTemplate.getMessageConverters());
		FakeStoreProductDTO fakeStoreProductDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id,
				HttpMethod.PUT, requestCallback, responseExtractor);
		return ProductConverter.convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
	}

	@Override
	public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) {
		RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDTO, FakeStoreProductDTO.class);
		HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(
				FakeStoreProductDTO.class, restTemplate.getMessageConverters());
		FakeStoreProductDTO fakeStoreProductDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id,
				HttpMethod.PATCH, requestCallback, responseExtractor);
		return ProductConverter.convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
	}

	@Override
	public void deleteProduct(Long id) {
		restTemplate.delete("https://fakestoreapi.com/products/" + id);
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
