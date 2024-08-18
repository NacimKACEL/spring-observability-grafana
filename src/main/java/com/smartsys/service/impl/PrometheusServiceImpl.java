package com.smartsys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.smartsys.service.PrometheusService;

@Service
public class PrometheusServiceImpl implements PrometheusService {

    private final String prometheusUrl = "http://prometheus:9090";
    private final WebClient webClient;

    public PrometheusServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(prometheusUrl).build();
    }

    public String queryPrometheus(String query, String start, String end, String step) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/api/v1/query")
                .queryParam("query", query)
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("step", step)
                .build())
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}
