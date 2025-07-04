package com.vk.ecommerce.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vk.ecommerce.dtos.search.FilterDto;
import com.vk.ecommerce.dtos.search.SortingCriteria;
import com.vk.ecommerce.models.Product;

public interface SearchService {

	/**
	 * Searches for products based on the search term, filters, and sorting criteria.
	 *
	 * @param searchTerm      The term to search for.
	 * @param filters         The list of filters to apply.
	 * @param sortingCriteria The criteria for sorting the results.
	 * @return A SearchResponseDTO containing the search results.
	 */
	public Page<Product> search(String searchTerm, List<FilterDto> filters, SortingCriteria sortingCriteria, int pageNumber, int pageSize);
	
	/**
	 * Performs a simple search for products based on a query string and category ID.
	 * @param query
	 * @param categoryId
	 * @param pageNumber
	 * @param pageSize
	 * @param sortingCriteria
	 * @return
	 */
	public Page<Product> simpleSearch(String query, Long categoryId,  int pageNumber, int pageSize, SortingCriteria sortingCriteria);

}
