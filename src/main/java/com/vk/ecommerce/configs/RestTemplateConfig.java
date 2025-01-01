package com.vk.ecommerce.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.vk.ecommerce.converters.ProductConverter;

@Configuration
public class RestTemplateConfig {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplateBuilder().build();
	}
}
