package com.vk.ecommerce.services.filteringService;

import com.vk.ecommerce.models.Product;

import java.util.List;

public interface Filter {

    List<Product> apply(List<Product> products,
                        List<String> allowedValues);
}
