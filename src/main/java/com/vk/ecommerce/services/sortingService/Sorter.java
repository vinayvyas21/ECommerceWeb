package com.vk.ecommerce.services.sortingService;

import com.vk.ecommerce.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);
}
