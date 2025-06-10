package com.vk.ecommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends BaseModel {
	private String title;
	private double price;
	private String description;
	private String imageUrl;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;

	public Product(String title, double price) {
		this.title = title;
		this.price = price;
	}

}
