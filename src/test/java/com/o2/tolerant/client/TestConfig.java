package com.o2.tolerant.client;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class TestConfig {

	// Additional Test Configurations if any

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	@Bean
	public SimpleRestClient restClient() {
		SimpleRestClient restClient = new SimpleRestClient();
		return restClient;
	}
}
