package com.smartsys.service;

import org.springframework.stereotype.Service;

@Service
public interface PrometheusService {
    public String queryPrometheus(String query, String start, String end, String step);
}
