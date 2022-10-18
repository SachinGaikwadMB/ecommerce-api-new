package com.mb.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "ECommerce API", version = "1.0", 
        description = "A complete ecommerce API with Authentication and Authorization."))
public class EcommerceApiBySachinApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiBySachinApplication.class, args);
	}

}
