package com.wirehouse.simpleshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080/api/warehouse");
        server.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Alam Team");
        contact.setEmail("alam@example.com");

        return new OpenAPI()
                .info(new Info()
                        .title("Simple Shop Warehouse API")
                        .description("API Documentation buat Simple Shop Warehouse Management System")
                        .version("1.0.0")
                        .contact(contact))
                .addServersItem(server);
    }
}