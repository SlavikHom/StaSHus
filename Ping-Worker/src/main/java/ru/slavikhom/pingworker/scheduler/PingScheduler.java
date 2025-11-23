package ru.slavikhom.pingworker.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.slavikhom.pingworker.config.RabbitConfig;
import ru.slavikhom.pingworker.dto.StatusChangeMessage;
import ru.slavikhom.pingworker.model.MonitoredServer;
import ru.slavikhom.pingworker.model.ServerStatus;
import ru.slavikhom.pingworker.repository.ServerRepository;
import ru.slavikhom.pingworker.service.PingService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PingScheduler {
    private final PingService pingService;
    private final ServerRepository serverRepository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 60000)
    public void checkServers(){
        List<MonitoredServer> servers = serverRepository.findAll();

        for (MonitoredServer server : servers) {
            boolean isReachable = pingService.isReachable(server.getAddress());
            ServerStatus newStatus = isReachable ? ServerStatus.UP : ServerStatus.DOWN;

            if (!server.getStatus().equals(newStatus)) {
                System.out.println("STATUS CHANGED: " + server.getAddress() + " -> " + newStatus);

                if (server.getStatus() != ServerStatus.UNKNOWN) {
                    StatusChangeMessage message = new StatusChangeMessage(
                            server.getAddress(),
                            server.getStatus(),
                            newStatus,
                            server.getOwner()
                    );
                    rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
                }

                server.setStatus(newStatus);
                serverRepository.save(server);
            }
        }
    }
}
