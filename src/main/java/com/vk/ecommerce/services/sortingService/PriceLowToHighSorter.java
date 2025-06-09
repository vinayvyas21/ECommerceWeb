package com.vk.ecommerce.services.sortingService;

import com.vk.ecommerce.models.Product;

import java.util.List;

public class PriceLowToHighSorter implements Sorter {

    @Override
    public List<Product> sort(List<Product> products) {
        return List.of(products.stream()
				.sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
				.toArray(Product[]::new));
    }
}
