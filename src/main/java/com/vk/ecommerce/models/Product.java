package com.vk.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
	
	private String title;
	private double price;
	private String description;
	private String image;
	@ManyToOne
	private Category category;

}
