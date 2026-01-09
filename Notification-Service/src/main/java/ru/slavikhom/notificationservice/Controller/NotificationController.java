package ru.slavikhom.notificationservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slavikhom.notificationservice.model.NotificationEvent;
import ru.slavikhom.notificationservice.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification History", description = "Просмотр истории событий")
@SecurityRequirement(name = "bearerAuth")
public class NotificationController {

    private final NotificationService service;

    @Operation(
            summary = "Получить историю уведомлений",
            description = "Возвращает список всех изменений статусов серверов для текущего пользователя"
    )
    @GetMapping("/history")
    public ResponseEntity<List<NotificationEvent>> getHistory(
            @Parameter(hidden = true)
            @RequestHeader("X-User-Name") String username) {
        return ResponseEntity.ok(service.getUserHistory(username));
    }
}