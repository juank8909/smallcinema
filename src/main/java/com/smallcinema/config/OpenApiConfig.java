package com.smallcinema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI consumerApiConfig() {
        return new OpenAPI().info(new Info().title("Small cinema API Spec")
                .description("Small cinema API specification (small cinema project)").version("1.0.0"));
    }
}
