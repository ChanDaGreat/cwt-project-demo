package com.cwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	  @Bean
	    public Docket api() {

	        return new Docket(DocumentationType.SWAGGER_2)
	        		.apiInfo(metaData())
	        		.select()
	        		.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	        		.paths(PathSelectors.any())
	        		.build();
	                
	    }

	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Customer Api")
	                .description("\"Swagger configuration for application \"")
	                .version("1.0")
	                .build();
	                
	    }
}
