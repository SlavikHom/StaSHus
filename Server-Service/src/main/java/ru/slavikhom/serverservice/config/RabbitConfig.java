package ru.slavikhom.serverservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_TASKS = "ping_tasks";
    public static final String QUEUE_RESULTS = "ping_results";
    public static final String QUEUE_NOTIFICATIONS = "status_notifications";

    @Bean
    public Queue tasksQueue() {
        return new Queue(QUEUE_TASKS, true);
    }

    @Bean
    public Queue resultsQueue() {
        return new Queue(QUEUE_RESULTS, true);
    }

    @Bean
    public MessageConverter converter() {
        return new JacksonJsonMessageConverter();
    }
}
