package ru.slavikhom.pingworker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slavikhom.pingworker.model.MonitoredServer;

import java.util.List;
import java.util.Optional;

public interface ServerRepository extends JpaRepository<MonitoredServer, Long> {

}
