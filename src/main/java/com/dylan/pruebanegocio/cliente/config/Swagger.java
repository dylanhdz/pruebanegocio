package com.dylan.pruebanegocio.cliente.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI documentacion() {
        return new OpenAPI()
                .info(new Info()
                    .title("Prueba Práctica de Servicios - API de Dylan Hernández")
                    .version("1.0")
                    .description("API para gestionar Clientes y sus Direcciones. Este es un requerimiento de la prueba práctica."));
    }
}
