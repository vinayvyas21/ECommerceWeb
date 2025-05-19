package com.vk.ecommerce.converters;

import java.util.ArrayList;
import java.util.List;

import com.vk.ecommerce.dtos.FakeStoreProductDTO;
import com.vk.ecommerce.models.Category;
import com.vk.ecommerce.models.Product;

/**
 * This class is responsible for converting FakeStoreProductDTO objects to Product objects.
 * It contains methods to convert a single FakeStoreProductDTO to a Product and to convert an array of FakeStoreProductDTOs to a list of Products.
 */
public class ProductConverter {

	public static Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {

		Product product = new Product();

		product.setId(fakeStoreProductDTO.getId());
		product.setTitle(fakeStoreProductDTO.getTitle());
//		product.setPrice(fakeStoreProductDTO.getPrice());
		product.setImageUrl(fakeStoreProductDTO.getImage());
		product.setDescription(fakeStoreProductDTO.getDescription());

		Category category = new Category();
		category.setName(fakeStoreProductDTO.getCategory());

		product.setCategory(category);
		return product;
	}

	public static List<Product> convertFakeStoreProductsListToProductList(FakeStoreProductDTO[] fakeStoreProductList) {
		List<Product> productsList = new ArrayList<>();
		for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductList) {
			productsList.add(convertFakeStoreProductDTOToProduct(fakeStoreProductDTO));
		}
		return productsList;
	}

}
