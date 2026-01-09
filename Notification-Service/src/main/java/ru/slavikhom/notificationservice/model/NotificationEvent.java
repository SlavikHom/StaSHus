package ru.slavikhom.notificationservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Событие изменения статуса сервера (История)")
public class NotificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID записи", example = "55", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Schema(description = "Адрес сервера", example = "192.168.0.1")
    private String serverAddress;

    @Schema(description = "Старый статус", example = "UP")
    private String oldStatus;

    @Schema(description = "Новый статус", example = "DOWN")
    private String newStatus;

    @Schema(description = "Владелец сервера", example = "user")
    private String userHandle;

    @Schema(description = "Время события", example = "2023-10-05T14:30:00")
    private LocalDateTime timestamp;
}