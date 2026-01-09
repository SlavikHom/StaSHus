package ru.slavikhom.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SigninRequest {

    @Schema(description = "Никнейм пользователя", example = "user")
    @NotBlank
    private String handle;

    @Schema(description = "Пароль", example = "password")
    @NotBlank
    private String password;
}