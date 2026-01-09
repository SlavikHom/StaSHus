package ru.slavikhom.notificationservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notification Service API",
                description = "Сервис хранения истории уведомлений о падении/поднятии серверов",
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}