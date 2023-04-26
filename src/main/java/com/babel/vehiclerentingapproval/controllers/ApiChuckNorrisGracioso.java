package com.babel.vehiclerentingapproval.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiChuckNorrisGracioso {
    @GetMapping(value = "/apiExterna")
    private String getJokeRamdon(){
        return new RestTemplate().getForObject("https://api.chucknorris.io/jokes/random",String.class);
    }
    @GetMapping(value= "/categoriasChistes")
    private Object getJokeCategoryList(){
        return new RestTemplate().getForObject("https://api.chucknorris.io/jokes/categories",String.class);
    }
}
