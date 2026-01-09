package ru.slavikhom.serverservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Server Monitoring API",
                description = "API для управления списком серверов и для мониторинга доступности",
                contact = @Contact(
                        name = "SlavikHom",
                        url = "https://github.com/SlavikHom"
                )
        )
)
public class OpenApiConfig {
}