package com.vk.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vk.ecommerce.dtos.ProductRequestDTO;
import com.vk.ecommerce.models.Category;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.repositories.CategoryRepository;
import com.vk.ecommerce.repositories.ProductRepository;
import com.vk.ecommerce.services.impl.SelfProductServiceImpl;

class SelfProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private SelfProductServiceImpl selfProductService;

	public SelfProductServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("Get product by ID returns product when found")
	@Test
	void getProductByIdReturnsProductWhenFound() {
		Product product = new Product("Product 1", 100.0);
		product.setId(1L);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Product result = selfProductService.getProductById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Product 1", result.getTitle());
	}

	@DisplayName("Get product by ID returns null when not found")
	@Test
	void getProductByIdReturnsNullWhenNotFound() {
		when(productRepository.findById(1L)).thenReturn(Optional.empty());

		Product result = selfProductService.getProductById(1L);

		assertNull(result);
	}

	@DisplayName("Get all products returns list of products")
	@Test
	void getAllProductsReturnsListOfProducts() {
		Product product1 = new Product("Product 1", 100.0);
		product1.setId(1L);
		Product product2 = new Product("Product 2", 200.0);
		product2.setId(2L);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

		List<Product> result = selfProductService.getAllProducts();

		assertEquals(2, result.size());
		assertEquals("Product 1", result.get(0).getTitle());
		assertEquals("Product 2", result.get(1).getTitle());
	}

	@DisplayName("Replace product updates and returns product")
	@Test
	void replaceProductUpdatesAndReturnsProduct() {
		Product product = new Product("Old Product", 100.0);
		product.setId(1L);
		ProductRequestDTO productRequestDTO = new ProductRequestDTO("New Product", 150.0);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = selfProductService.replaceProduct(1L, productRequestDTO);

		assertNotNull(result);
		assertEquals("New Product", result.getTitle());
		assertEquals(150.0, result.getPrice());
	}

	@DisplayName("Update product partially updates and returns product")
	@Test
	void updateProductPartiallyUpdatesAndReturnsProduct() {
		Product product = new Product("Old Product", 100.0);
		product.setId(1L);
		ProductRequestDTO productRequestDTO = new ProductRequestDTO("Updated Product", null);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = selfProductService.updateProduct(1L, productRequestDTO);

		assertNotNull(result);
		assertEquals("Updated Product", result.getTitle());
		assertEquals(100.0, result.getPrice());
	}

	@DisplayName("Delete product removes product when found")
	@Test
	void deleteProductRemovesProductWhenFound() {
		Product product = new Product("Product 1", 100.0);
		product.setId(1L);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		doNothing().when(productRepository).delete(product);

		selfProductService.deleteProduct(1L);

		verify(productRepository, times(1)).delete(product);
	}

	@DisplayName("Create product saves and returns product with new category")
	@Test
	void createProductSavesAndReturnsProductWithNewCategory() {
		Category category = new Category();
		category.setName("New Category");
		category.setId(null);
		Product product = new Product("Product 1", 100.0);
		product.setId(1L);
		product.setCategory(category);
		Category newCat = new Category();
		newCat.setId(1L);
		newCat.setName("New Category");
		when(categoryRepository.save(any(Category.class))).thenReturn(newCat);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = selfProductService.createProduct(product);

		assertNotNull(result);
		assertNotNull(result.getCategory().getId());
		assertEquals("New Category", result.getCategory().getName());
	}
}
