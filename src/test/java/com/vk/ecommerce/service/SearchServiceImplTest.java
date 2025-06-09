package com.vk.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.vk.ecommerce.dtos.search.FilterDto;
import com.vk.ecommerce.dtos.search.SortingCriteria;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.repositories.ProductRepository;
import com.vk.ecommerce.services.filteringService.FilterFactory;
import com.vk.ecommerce.services.impl.SearchServiceImpl;
import com.vk.ecommerce.services.sortingService.SorterFactory;

class SearchServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private FilterFactory filterFactory;

	@Mock
	private SorterFactory sorterFactory;

	@InjectMocks
	private SearchServiceImpl searchService;

	public SearchServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("Search returns paginated products matching query and filters")
	@Test
	void searchReturnsPaginatedProductsMatchingQueryAndFilters() {

		Product p1 = new Product("Product 1", 100.0);
		p1.setId(1L);
		p1.setDescription("Product 1 description");
		Product p2 = new Product("Product 2", 200.0);
		p2.setId(2L);
		p2.setDescription("Product 2 description");

		List<Product> products = Arrays.asList(p1);
		when(productRepository.findByTitleContaining("my product 1")).thenReturn(products);

		FilterDto filterDto = new FilterDto();
		filterDto.setKey("ram");
		filterDto.setValues(Collections.singletonList("value"));

		Page<Product> result = searchService.search("my product 1", Collections.singletonList(filterDto),
				SortingCriteria.PRICE_LOW_TO_HIGH, 1, 1);

		assertNotNull(result);
		assertEquals(1, result.getContent().size());
		assertEquals("Product 1", result.getContent().get(0).getTitle());
	}

	@DisplayName("Search handles empty product list gracefully")
	@Test
	void searchHandlesEmptyProductListGracefully() {
		when(productRepository.findByTitleContaining("query")).thenReturn(Collections.emptyList());

		Page<Product> result = searchService.search("query", Collections.emptyList(), SortingCriteria.PRICE_LOW_TO_HIGH,
				1, 1);

		assertNotNull(result);
		assertTrue(result.getContent().isEmpty());
	}

	@DisplayName("Simple search returns paginated products matching query and category")
	@Test
	void simpleSearchReturnsPaginatedProductsMatchingQueryAndCategory() {
		Product p1 = new Product("Product 1", 100.0);
		p1.setId(1L);
		p1.setDescription("Product 1 description");
		Product p2 = new Product("Product 2", 200.0);
		p2.setId(2L);
		p2.setDescription("Product 2 description");
		List<Product> products = Arrays.asList(p1, p2);
		Page<Product> paginatedProducts = new PageImpl<>(products, PageRequest.of(0, 2), products.size());
		when(productRepository.findAllByTitleContainingAndCategory_Id("query", 1L,
				PageRequest.of(0, 2, Sort.by("price").descending()))).thenReturn(paginatedProducts);

		Page<Product> result = searchService.simpleSearch("query", 1L, 0, 2, "price");

		assertNotNull(result);
		assertEquals(2, result.getContent().size());
		assertEquals("Product 1", result.getContent().get(0).getTitle());
	}

	@DisplayName("Simple search handles no matching products")
	@Test
	void simpleSearchHandlesNoMatchingProducts() {
		Page<Product> emptyPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 2), 0);
		when(productRepository.findAllByTitleContainingAndCategory_Id("query", 1L,
				PageRequest.of(0, 2, Sort.by("price").descending()))).thenReturn(emptyPage);

		Page<Product> result = searchService.simpleSearch("query", 1L, 0, 2, "price");

		assertNotNull(result);
		assertTrue(result.getContent().isEmpty());
	}
}
