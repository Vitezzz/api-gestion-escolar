package com.sige.api_gestion_escolar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Sistema de Gestión Escolar Cuatrimestral API",
        version = "1.0.0",
        description = "API REST para la gestión de un sistema escolar cuatrimestral",
        contact = @Contact(
            name = "Equipo de Desarrollo",
            email = "soporte@escuela.edu"
        )
    ),
    security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "JWT token para autenticación. Formato: 'Bearer {token}'"
)
public class ApiGestionEscolarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGestionEscolarApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("Sistema Escolar Cuatrimestral - Iniciado");
        System.out.println("========================================");
        System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("API Docs: http://localhost:8080/api-docs");
        System.out.println("========================================\n");
    }
}