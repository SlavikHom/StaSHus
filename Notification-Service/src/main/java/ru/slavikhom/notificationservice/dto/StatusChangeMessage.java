package ru.slavikhom.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сообщение об изменении статуса")
public class StatusChangeMessage {

    @Schema(description = "Адрес сервера", example = "192.168.0.1")
    private String serverAddress;

    @Schema(description = "Старый статус", example = "UP")
    private String oldStatus;

    @Schema(description = "Новый статус", example = "DOWN")
    private String newStatus;

    @Schema(description = "Никнейм владельца", example = "user")
    private String ownerHandle;
}