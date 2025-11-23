package ru.slavikhom.serverservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slavikhom.serverservice.model.MonitoredServer;

import java.util.List;
import java.util.Optional;

public interface ServerRepository extends JpaRepository<MonitoredServer, Long> {
    List<MonitoredServer> findAllByOwner(String owner);
    Optional<MonitoredServer> findByIdAndOwner(Long id, String owner);
}
