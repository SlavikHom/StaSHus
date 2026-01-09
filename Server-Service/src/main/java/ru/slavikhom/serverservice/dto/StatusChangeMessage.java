package ru.slavikhom.serverservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.slavikhom.serverservice.model.ServerStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Событие изменения статуса сервера (для уведомлений)")
public class StatusChangeMessage {

    @Schema(description = "Адрес сервера", example = "myserver.com")
    private String serverAddress;

    @Schema(description = "Предыдущий статус", example = "UP")
    private ServerStatus oldStatus;

    @Schema(description = "Новый статус", example = "DOWN")
    private ServerStatus newStatus;

    @Schema(description = "Владелец сервера (кому слать уведомление)", example = "user")
    private String ownerHandle;
}