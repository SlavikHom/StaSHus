package ru.slavikhom.serverservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результат попытки пинга")
public class PingResult {

    @Schema(description = "ID проверяемого сервера", example = "101")
    private Long serverId;

    @Schema(description = "Доступен ли сервер", example = "true")
    private boolean reachable;
}