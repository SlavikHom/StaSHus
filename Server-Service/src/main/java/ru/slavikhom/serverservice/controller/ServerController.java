package ru.slavikhom.serverservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slavikhom.serverservice.model.MonitoredServer;
import ru.slavikhom.serverservice.service.ServerService;

import java.util.List;

@RestController
@RequestMapping("/servers")
@RequiredArgsConstructor
@Tag(name = "Server Management", description = "Управление списком серверов для мониторинга")
@SecurityRequirement(name = "bearerAuth")
public class ServerController {
    private final ServerService serverService;

    @Data
    @Schema(description = "Запрос на добавление сервера")
    static class ServerRequest {
        @Schema(description = "IP адрес или домен сервера", example = "192.168.1.10")
        private String address;
    }

    @Operation(summary = "Получить список серверов", description = "Возвращает все серверы текущего пользователя")
    @GetMapping
    public ResponseEntity<List<MonitoredServer>> getServers(
            @Parameter(hidden = true)
            @RequestHeader("X-User-Name") String username) {
        return ResponseEntity.ok(serverService.getServersByOwner(username));
    }

    @Operation(summary = "Добавить сервер", description = "Добавляет сервер в список для пинга")
    @PostMapping
    public ResponseEntity<MonitoredServer> addServer(
            @RequestBody ServerRequest request,
            @Parameter(hidden = true)
            @RequestHeader("X-User-Name") String username) {
        return ResponseEntity.ok(
                serverService.addServer(request.getAddress(), username)
        );
    }

    @Operation(summary = "Удалить сервер", description = "Прекращает мониторинг сервера")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(
            @Parameter(description = "ID сервера")
            @PathVariable Long id,
            @Parameter(hidden = true)
            @RequestHeader("X-User-Name") String username) {
        serverService.deleteServer(id, username);
        return ResponseEntity.ok().build();
    }
}
