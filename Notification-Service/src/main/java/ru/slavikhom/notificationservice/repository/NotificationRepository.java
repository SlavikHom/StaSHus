package ru.slavikhom.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slavikhom.notificationservice.model.NotificationEvent;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEvent, Long> {
    List<NotificationEvent> findByUserHandleOrderByTimestampDesc(String userHandle);
}
