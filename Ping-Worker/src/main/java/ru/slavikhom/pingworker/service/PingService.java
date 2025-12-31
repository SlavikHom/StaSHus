package ru.slavikhom.pingworker.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class PingService {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public boolean isReachable(String address) {
        String urlString = address.startsWith("https://") ? address : "https://" + address;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<Void> response= httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            return response.statusCode() >= 200 && response.statusCode() < 400;
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            return false;
        }
    }
}
