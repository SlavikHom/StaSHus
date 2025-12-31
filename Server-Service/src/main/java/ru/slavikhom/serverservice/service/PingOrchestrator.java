package ru.slavikhom.serverservice.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.slavikhom.serverservice.config.RabbitConfig;
import ru.slavikhom.serverservice.dto.PingResult;
import ru.slavikhom.serverservice.dto.PingTask;
import ru.slavikhom.serverservice.dto.StatusChangeMessage;
import ru.slavikhom.serverservice.model.MonitoredServer;
import ru.slavikhom.serverservice.model.ServerStatus;
import ru.slavikhom.serverservice.repository.ServerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class PingOrchestrator {
    private final ServerRepository serverRepository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 60000)
    public void scheduledPingTasks() {
        log.info("Scheduling ping tasks...");
        int page = 0;
        int batchSize = 100;

        while (true) {
            Page<MonitoredServer> serverPage = serverRepository.findAll(PageRequest.of(page, batchSize));

            for (MonitoredServer server : serverPage.getContent()) {
                PingTask task = new PingTask(server.getId(), server.getAddress());
                rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_TASKS, task);
            }

            if (!serverPage.hasNext()) {
                break;
            }
            page++;
        }
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_RESULTS)
    @Transactional
    public void handlePingResult(PingResult result) {
        serverRepository.findById(result.getServerId()).ifPresentOrElse(server -> {
            ServerStatus newStatus = result.isReachable() ? ServerStatus.UP : ServerStatus.DOWN;

            if (!server.getStatus().equals(newStatus)) {
                log.info("Status changed for server {}: {} -> {}", server.getAddress(), server.getStatus(), newStatus);

                if (!server.getStatus().equals(ServerStatus.UNKNOWN)) {
                    StatusChangeMessage msg = new StatusChangeMessage(
                            server.getAddress(),
                            server.getStatus(),
                            newStatus,
                            server.getOwner()
                    );
                    rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NOTIFICATIONS, msg);
                }

                server.setStatus(newStatus);
                serverRepository.save(server);
            }
        }, () -> log.warn("Received result for unknown server id: {}", result.getServerId()));
    }
}
