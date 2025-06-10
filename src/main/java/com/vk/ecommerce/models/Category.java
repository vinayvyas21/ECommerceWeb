package com.vk.ecommerce.models;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseModel {
	@Column(nullable = false, unique = true, name = "category_name")
	private String name;

	@Basic(fetch = FetchType.LAZY)
	private String description;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Product> featuredProducts;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@Fetch(FetchMode.SELECT)
	private List<Product> allProducts;

	@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
	private List<Category> subcategories;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;

	private int countOfProducts;

}
