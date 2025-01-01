package com.vk.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {
	
	private Long id;
	private String title;
	private double price;
	private String description;
	private String image;

}
