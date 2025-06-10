package com.vk.ecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vk.ecommerce.dtos.products.GetProductDto;
import com.vk.ecommerce.dtos.search.FilterDto;
import com.vk.ecommerce.dtos.search.SearchResponseDto;
import com.vk.ecommerce.dtos.search.SortingCriteria;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.services.SearchService;

/**
 * Search Controller
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	private SearchService searchService;

	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Searches for products based on the search term, filters, and sorting
	 * criteria.
	 *
	 * @param query           The term to search for.
	 * @param filters         The list of filters to apply.
	 * @param sortingCriteria The criteria for sorting the results.
	 * @param pageNumber      The page number to retrieve.
	 * @param pageSize        The number of items per page.
	 * @return A SearchResponseDTO containing the search results.
	 */
	@GetMapping("/")
	public SearchResponseDto searchProducts(@RequestParam("query") String query,
			@RequestParam("filters") List<FilterDto> filters, @RequestParam("sortBy") SortingCriteria sortingCriteria,
			@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {

		SearchResponseDto response = new SearchResponseDto();
		Page<Product> productsPage = searchService.search(query, filters, sortingCriteria, pageNumber, pageSize);

		List<GetProductDto> getProductDtos = productsPage.getContent().stream().map(GetProductDto::from)
				.collect(Collectors.toList());

		Pageable pageable = PageRequest.of(productsPage.getNumber(), productsPage.getSize(), productsPage.getSort());
		Page<GetProductDto> getProductDtoPage = new PageImpl<>(getProductDtos, pageable,
				productsPage.getTotalElements());

		response.setProductsPage(getProductDtoPage);

		return response;

	}

	/**
	 * Performs a simple search for products based on a query string and category ID.
	 * @param query
	 * @param categoryId
	 * @param pageNumber
	 * @param pageSize
	 * @param sortingCriteria
	 * @return
	 */
	@GetMapping("/byCategory")
	public SearchResponseDto simpleSearch(@RequestParam("query") String query,
			@RequestParam("category") Long categoryId, @RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize, @RequestParam("sortBy") SortingCriteria sortingCriteria) {
		SearchResponseDto response = new SearchResponseDto();
		Page<Product> productsPage = searchService.simpleSearch(query, categoryId, pageNumber, pageSize,
				sortingCriteria);
		List<GetProductDto> getProductDtos = productsPage.getContent().stream().map(GetProductDto::from)
				.collect(Collectors.toList());

		Pageable pageable = PageRequest.of(productsPage.getNumber(), productsPage.getSize(), productsPage.getSort());
		Page<GetProductDto> getProductDtoPage = new PageImpl<>(getProductDtos, pageable,
				productsPage.getTotalElements());

		response.setProductsPage(getProductDtoPage);

		return response;

	}

}
