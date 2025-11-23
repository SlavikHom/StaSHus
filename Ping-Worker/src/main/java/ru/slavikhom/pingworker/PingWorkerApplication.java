package ru.slavikhom.pingworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PingWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingWorkerApplication.class, args);
    }

}
