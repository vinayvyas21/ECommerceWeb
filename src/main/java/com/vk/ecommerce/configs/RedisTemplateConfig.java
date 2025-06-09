package com.vk.ecommerce.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Configuration class for RedisTemplate. This class provides a RedisTemplate
 * bean that can be used to interact with Redis.
 */
@Configuration
public class RedisTemplateConfig {

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		return template;
	}
}
