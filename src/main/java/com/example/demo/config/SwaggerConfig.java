package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workflow API")
                        .description("Spring Boot JWT Authentication API")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server()
                                .url("https://9156.408procr.amypo.ai")
                                .description("Production Server"),
                        new Server()
                                .url("http://localhost:9001")
                                .description("Local Development Server")
                ));
    }
}