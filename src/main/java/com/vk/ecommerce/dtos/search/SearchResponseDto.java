package com.vk.ecommerce.dtos.search;

import org.springframework.data.domain.Page;

import com.vk.ecommerce.dtos.products.GetProductDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseDto {
    private Page<GetProductDto> productsPage;
}
