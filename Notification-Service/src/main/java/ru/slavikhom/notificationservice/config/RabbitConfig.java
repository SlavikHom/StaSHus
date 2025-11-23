package ru.slavikhom.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.slavikhom.notificationservice.dto.StatusChangeMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "status_notifications";

    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*");
        Map<String, Class<?>> idMapping = new HashMap<>();
        idMapping.put("ru.slavikhom.pingworker.dto.StatusChangeMessage", StatusChangeMessage.class);
        classMapper.setIdClassMapping(idMapping);
        converter.setClassMapper(classMapper);
        return converter;
    }
}
