package com.vk.ecommerce.services.filteringService;

import java.util.ArrayList;
import java.util.List;

import com.vk.ecommerce.models.Product;

public class RAMFilter implements Filter {

    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
    	
    	// Filter products based on the RAM value
        List<Product> ans = new ArrayList<>();

        for (Product product: products) {
            if (product.getDescription().contains("")) {
                ans.add(product);
            }
        }

        return ans;
    }
}
