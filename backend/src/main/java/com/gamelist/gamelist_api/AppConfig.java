package com.gamelist.gamelist_api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    @Bean
    public OpenAPI gameVaultOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("GameVault API")
                .description("API REST para gestión de videojuegos, categorías, plataformas, reseñas y wishlist")
                .version("1.0.0"))
            .servers(List.of(
                new Server().url("/").description("Servidor actual")
            ));
    }
}
