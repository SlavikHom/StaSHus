package ru.slavikhom.serverservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сервер, находящийся на мониторинге")
public class MonitoredServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID записи", example = "105", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Column(nullable = false)
    @Schema(description = "IP адрес или домен", example = "87.250.250.242")
    private String address;

    @Column(nullable = false)
    @Schema(description = "Владелец сервера (handle)", example = "user")
    private String owner;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Текущий статус доступности")
    private ServerStatus status;
}