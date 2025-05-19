package com.vk.ecommerce.dtos.products;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllProductsResponseDto {
    private List<GetProductDto> products;
}
