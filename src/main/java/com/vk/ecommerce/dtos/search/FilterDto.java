package com.vk.ecommerce.dtos.search;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {
    private String key;
    private List<String> values;
}
