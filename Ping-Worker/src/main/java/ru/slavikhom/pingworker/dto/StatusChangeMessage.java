package ru.slavikhom.pingworker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.slavikhom.pingworker.model.ServerStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusChangeMessage {
    private String serverAddress;
    private ServerStatus oldStatus;
    private ServerStatus newStatus;
    private String ownerHandle;
}
