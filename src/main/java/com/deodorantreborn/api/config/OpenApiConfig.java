package com.deodorantreborn.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Deodorant Reborn API")
                        .description("REST API for the IntelliJ Deodorant Reborn community platform")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Dein Name")
                                .email("datengtankoua@gmail.com")
                                .url("https://deodorant-reborn-platform.vercel.app"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}