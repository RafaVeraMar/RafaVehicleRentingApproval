package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.services.ChuckNorrisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ApiChuckNorrisGracioso {
    public ApiChuckNorrisGracioso(ChuckNorrisService chuckNorrisService) {
        this.chuckNorrisService = chuckNorrisService;
    }

    ChuckNorrisService chuckNorrisService;
    @GetMapping(value = "/randomJoke")
    public String getJokeRamdon(){
        return chuckNorrisService.getRandomJoke();
    }
    @GetMapping(value= "/categoriasChistes")
    private Object getJokeCategoryList(){
        return new RestTemplate().getForObject("https://api.chucknorris.io/jokes/categories",String.class);
    }
}
