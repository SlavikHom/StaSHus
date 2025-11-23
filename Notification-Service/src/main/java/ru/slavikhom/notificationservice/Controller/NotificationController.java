package ru.slavikhom.notificationservice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slavikhom.notificationservice.model.NotificationEvent;
import ru.slavikhom.notificationservice.repository.NotificationRepository;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationRepository repository;

    @GetMapping("/history")
    public ResponseEntity<List<NotificationEvent>> getHistory(
            @RequestHeader("X-User-Name") String username) {
        return  ResponseEntity.ok(
                repository.findByUserHandleOrderByTimestampDesc(username)
        );
    }
}
