package ru.slavikhom.serverservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.slavikhom.serverservice.model.MonitoredServer;
import ru.slavikhom.serverservice.model.ServerStatus;
import ru.slavikhom.serverservice.repository.ServerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {
    private final ServerRepository serverRepository;

    public MonitoredServer addServer(String address, String owner) {
        MonitoredServer server = MonitoredServer.builder()
                .address(address)
                .owner(owner)
                .status(ServerStatus.UNKNOWN)
                .build();
        return serverRepository.save(server);
    }

    public List<MonitoredServer> getServersByOwner(String owner) {
        return serverRepository.findAllByOwner(owner);
    }

    public void deleteServer(Long id, String owner) {
        MonitoredServer server = serverRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new RuntimeException("Server with id " + id + " not found"));
        serverRepository.delete(server);
    }

    public void updateServer(Long id, ServerStatus status) {
        MonitoredServer server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server with id " + id + " not found"));
        server.setStatus(status);
        serverRepository.save(server);
    }
}
