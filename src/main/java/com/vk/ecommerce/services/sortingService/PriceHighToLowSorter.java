package com.vk.ecommerce.services.sortingService;

import java.util.List;

import com.vk.ecommerce.models.Product;

public class PriceHighToLowSorter implements Sorter {

    @Override
    public List<Product> sort(List<Product> products) {
        return List.of(products.stream()
				.sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
				.toArray(Product[]::new));
    }
}
