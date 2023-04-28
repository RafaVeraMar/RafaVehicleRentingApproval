package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.services.ChuckNorrisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ApiChuckController {
    public ApiChuckController(ChuckNorrisService chuckNorrisService) {
        this.chuckNorrisService = chuckNorrisService;
    }
    ChuckNorrisService chuckNorrisService;
    @GetMapping(value = "/randomJoke")
    public ResponseEntity getRandomJoke(){
        return new ResponseEntity<>(Map.of("Chiste",chuckNorrisService.getRandomJoke()), HttpStatus.OK);
    }
    @GetMapping(value= "/jokeCategories")
    public ResponseEntity getJokeCategories(){
        return new ResponseEntity<>(Map.of("Categorias",chuckNorrisService.getJokeCategoryList()), HttpStatus.OK);
    }
}
