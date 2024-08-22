package com.blog.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")  // Equivalent to PathSelectors.any()
                .build();
    }

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Blog API")
                .version("1.0")
                .description("Documentation for the Blog API")
                .contact(new Contact()
                    .name("Ashutosh Dikondwar")
                    .email("ashutoshdikondwar@gmail.com")))
            .addSecurityItem(new SecurityRequirement().addList("bearerToken"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerToken", new SecurityScheme()
                    .name("bearerToken")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
	 
	 
}
