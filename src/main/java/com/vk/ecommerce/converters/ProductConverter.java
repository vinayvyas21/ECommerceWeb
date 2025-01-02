package com.vk.ecommerce.converters;

import java.util.ArrayList;
import java.util.List;

import com.vk.ecommerce.dtos.FakeStoreProductDTO;
import com.vk.ecommerce.models.Category;
import com.vk.ecommerce.models.Product;

public class ProductConverter {

	public static Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
		
		Product product = new Product();
		
		product.setId(fakeStoreProductDTO.getId());
		product.setTitle(fakeStoreProductDTO.getTitle());
		product.setPrice(fakeStoreProductDTO.getPrice());
		product.setImage(fakeStoreProductDTO.getImage());
		product.setDescription(fakeStoreProductDTO.getDescription());
		
		Category category = new Category();
		category.setTitle(fakeStoreProductDTO.getCategory());
		
		product.setCategory(category);
		return product;
	}
	
	public static List<Product> convertFakeStoreProductsListToProductList(FakeStoreProductDTO[] fakeStoreProductList) {
		List<Product> productsList = new ArrayList<>();
		for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductList) {
			productsList.add(convertFakeStoreProductDTOToProduct(fakeStoreProductDTO));
		}
		return productsList;
	}

}
