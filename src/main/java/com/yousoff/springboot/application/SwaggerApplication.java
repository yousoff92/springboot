package com.yousoff.springboot.application;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * To verify, open http://localhost:8080/v2/api-docs
 * 
 * For Swagger UI 2, open http://localhost:8080/swagger-ui.html
 * 
 */

/**
 * 
 * @author Yousoff Effendy
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerApplication {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yousoff.springboot.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "Sample SpringBoot CRUD API", 
	       null, 
	       "v 1.0.0", 
	       "Open for non-commercial use.", 
	       new Contact("Yousoff Effendy Mohd Ali", "www.linkedin.com/in/yousoff-effendy-mohd-ali-3a976a98", "yousoff92@gmail.com"), 
	       "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}
