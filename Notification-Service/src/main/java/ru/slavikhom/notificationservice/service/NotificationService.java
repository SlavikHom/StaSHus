package ru.slavikhom.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.slavikhom.notificationservice.model.NotificationEvent;
import ru.slavikhom.notificationservice.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    public List<NotificationEvent> getUserHistory(String handle) {
        return repository.findByUserHandleOrderByTimestampDesc(handle);
    }

    public void saveNotification(NotificationEvent notificationEvent) {
        repository.save(notificationEvent);
    }
}
