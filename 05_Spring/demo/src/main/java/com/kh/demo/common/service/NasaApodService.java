package com.kh.demo.common.service;

import com.kh.demo.common.dto.ApodResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
public class NasaApodService {

    private final RestClient restClient;
    private final String apiKey;

    public NasaApodService(@Value("${nasa.api.base-url}") String baseUrl,
                           @Value("${nasa.api.key}") String apiKey) {
        this.restClient = RestClient.create(baseUrl);
        this.apiKey = apiKey;
    }

    public ApodResponse getTodayApod(){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/planetary/apod")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .body(ApodResponse.class);
    }
}
