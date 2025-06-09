package com.vk.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vk.ecommerce.dtos.search.FilterDto;
import com.vk.ecommerce.dtos.search.SortingCriteria;
import com.vk.ecommerce.models.Product;
import com.vk.ecommerce.repositories.ProductRepository;
import com.vk.ecommerce.services.SearchService;
import com.vk.ecommerce.services.filteringService.FilterFactory;
import com.vk.ecommerce.services.sortingService.SorterFactory;

/**
 * Search Service Implementation
 */
@Service
public class SearchServiceImpl implements SearchService {

	private ProductRepository productRepository;

	public SearchServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<Product> search(String query, List<FilterDto> filters, SortingCriteria sortingCriteria, int pageNumber,
			int pageSize) {
		List<Product> products = productRepository.findByTitleContaining(query);

		if (products.isEmpty()) {
			return new PageImpl<>(new ArrayList<>(), PageRequest.of(pageNumber, pageSize), 0);
		}
		for (FilterDto filterDto : filters) {
			products = FilterFactory.getFilterFromKey(filterDto.getKey()).apply(products, filterDto.getValues());
		}

		products = SorterFactory.getSorterByCriteria(sortingCriteria).sort(products);

		List<Product> productsOnPage = new ArrayList<>();

		for (int i = pageSize * (pageNumber - 1); i <= (pageSize * pageNumber) - 1; ++i) {
			productsOnPage.add(products.get(i));
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize); // Adjust page number and size as needed
		return new PageImpl<>(productsOnPage, pageable, products.size());
	}

	public Page<Product> simpleSearch(String query, Long categoryId, int pageNumber, int pageSize,
			String sortingAttribute) {
		// select * from products
		// where title like "%query%"
		// and categoryID = {categoryId}
		// limit {pageSize} offset pageNumbver * pageSize
		// orderBy sortingAttribute;
		Page<Product> products = productRepository.findAllByTitleContainingAndCategory_Id(query, categoryId,
				PageRequest.of(pageNumber, pageSize, Sort.by(sortingAttribute).descending()));

		return products;
	}

}
