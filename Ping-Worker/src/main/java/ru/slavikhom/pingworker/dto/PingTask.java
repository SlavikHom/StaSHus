package ru.slavikhom.pingworker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingTask {
    private Long serverId;
    private String address;
}
