package com.app.hsbc.excrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.app.hsbc.excrates.config.AppPropertyConfig;

@SpringBootApplication
public class ExchangeRateServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateServicesApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	      return builder.build();
	}

}
