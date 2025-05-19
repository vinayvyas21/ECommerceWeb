package com.vk.ecommerce.services.filteringService;

import java.util.List;

import com.vk.ecommerce.models.Product;

public class BrandFilter implements Filter {
	
	 
    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
       // Filter products based on the brand
		return null;
    }
}
