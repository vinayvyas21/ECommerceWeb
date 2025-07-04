package com.vk.ecommerce.dtos.fakestore;

import com.vk.ecommerce.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product) {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.title = product.getTitle();
        fakeStoreCreateProductRequestDto.description = product.getDescription();
        fakeStoreCreateProductRequestDto.category = product.getCategory().getName();
        fakeStoreCreateProductRequestDto.price = product.getPrice();
        fakeStoreCreateProductRequestDto.image = product.getImageUrl();

        return fakeStoreCreateProductRequestDto;
    }
}
