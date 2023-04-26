package com.babel.vehiclerentingapproval.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiChuckNorrisGracioso {
    @GetMapping(value = "/apiExterna")
    private String getJokeRamdon(){
        String uri = "https://api.chucknorris.io/jokes/random";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
}
