package ru.slavikhom.pingworker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.slavikhom.pingworker.config.RabbitConfig;
import ru.slavikhom.pingworker.dto.PingResult;
import ru.slavikhom.pingworker.dto.PingTask;

@Service
@Slf4j
@RequiredArgsConstructor
public class PingTaskListener {
    private final PingService pingService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.QUEUE_TASKS, concurrency = "10")
    public void handlePingTask(PingTask task) {
        boolean isReachable = pingService.isReachable(task.getAddress());

        PingResult result = new PingResult(task.getServerId(), isReachable);

        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_RESULTS, result);

        log.debug("Processed task for id: {}, result: {}", task.getServerId(), isReachable);
    }
}
