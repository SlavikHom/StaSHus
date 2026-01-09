package ru.slavikhom.serverservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Задача для воркера на проверку сервера")
public class PingTask {

    @Schema(description = "ID сервера", example = "101")
    private Long serverId;

    @Schema(description = "Адрес для пинга", example = "8.8.8.8")
    private String address;
}