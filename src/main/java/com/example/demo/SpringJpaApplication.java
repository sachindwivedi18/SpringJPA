package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCouchbaseRepositories
public class SpringJpaApplication {
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}
}