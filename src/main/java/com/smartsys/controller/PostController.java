package com.smartsys.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.smartsys.model.Post;

@RestController
public class PostController {

    private RestClient restClient;

    public PostController(RestClient.Builder restClient) {
        this.restClient = restClient.baseUrl("https://jsonplaceholder.typicode.com")
                                    .build();
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return this.restClient.get()
                            .uri("/posts")
                            .retrieve()
                            .body(new ParameterizedTypeReference<List<Post>>() {});
    }

}
