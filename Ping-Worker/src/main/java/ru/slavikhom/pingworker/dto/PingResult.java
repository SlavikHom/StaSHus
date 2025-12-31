package ru.slavikhom.pingworker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingResult {
    private Long serverId;
    private boolean reachable;
}
