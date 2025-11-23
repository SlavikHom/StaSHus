package ru.slavikhom.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.slavikhom.notificationservice.dto.StatusChangeMessage;
import ru.slavikhom.notificationservice.model.NotificationEvent;
import ru.slavikhom.notificationservice.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {
    private final NotificationRepository repository;

    @RabbitListener(queues = "status_notifications")
    public void handleMessage(StatusChangeMessage message) {
        log.info("RECEIVED ALERT for user [{}]: Server {} changed state {} -> {}",
                message.getOwnerHandle(),
                message.getServerAddress(),
                message.getOldStatus(),
                message.getNewStatus());

        NotificationEvent event = NotificationEvent.builder()
                .serverAddress(message.getServerAddress())
                .oldStatus(message.getOldStatus())
                .newStatus(message.getNewStatus())
                .userHandle(message.getOwnerHandle())
                .timestamp(LocalDateTime.now())
                .build();

        repository.save(event);
    }
}
