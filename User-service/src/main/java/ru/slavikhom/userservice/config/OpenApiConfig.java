package ru.slavikhom.userservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Service API",
                description = "API для управления пользователями и аутентификации",
                contact = @Contact(
                        name = "SlavikHom",
                        url = "https://github.com/SlavikHom"
                )
        )
)
public class OpenApiConfig {
}