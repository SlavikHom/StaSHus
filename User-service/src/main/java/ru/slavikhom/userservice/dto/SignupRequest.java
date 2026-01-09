package ru.slavikhom.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию пользователя")
public class SignupRequest {

    @Schema(description = "Уникальный никнейм пользователя", example = "user")
    @NotBlank
    private String handle;

    @Schema(description = "Электронная почта", example = "user@yandex.ru")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Пароль", example = "password")
    @NotBlank
    private String password;
}
