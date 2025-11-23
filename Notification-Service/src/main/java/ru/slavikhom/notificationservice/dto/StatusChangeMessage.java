package ru.slavikhom.notificationservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusChangeMessage {
    private String serverAddress;
    private String oldStatus;
    private String newStatus;
    private String ownerHandle;
}
