package ru.slavikhom.serverservice.controller;

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
public class ServerController {
    private final ServerService serverService;

    @Data
    static class ServerRequest {
        private String address;
    }

    @GetMapping
    public ResponseEntity<List<MonitoredServer>> getServers(
            @RequestHeader("X-User-Name") String username) {
        return ResponseEntity.ok(serverService.getServersByOwner(username));
    }

    @PostMapping
    public ResponseEntity<MonitoredServer> addServer(
            @RequestBody ServerRequest request,
            @RequestHeader("X-User-Name") String username) {
        return ResponseEntity.ok(
                serverService.addServer(request.getAddress(), username)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(
            @PathVariable Long id,
            @RequestHeader("X-User-Name") String username) {
        serverService.deleteServer(id, username);
        return ResponseEntity.ok().build();
    }
}
