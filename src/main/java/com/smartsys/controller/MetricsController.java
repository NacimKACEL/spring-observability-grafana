package com.smartsys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartsys.service.PrometheusService;

@RestController
public class MetricsController {

    private final PrometheusService prometheusService;

    public MetricsController(PrometheusService prometheusService) {
        this.prometheusService = prometheusService;
    }

    @GetMapping("/metrics")
    public String getMetrics(@RequestParam String query, @RequestParam String start, @RequestParam String end, @RequestParam String step) {
        return prometheusService.queryPrometheus(query, start, end, step);
    }
}
