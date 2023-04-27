package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.models.Joke;
import com.babel.vehiclerentingapproval.models.JokeCategories;
import com.babel.vehiclerentingapproval.services.ChuckNorrisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChuckNorrisServiceImpl implements ChuckNorrisService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String getRandomJoke() {
        String objectJoke = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", String.class);
        Joke joke = new Joke(objectJoke);
        return joke.toString();

    }

    @Override
    public JokeCategories getJokeCategoryList() {
        String jokeCategories= restTemplate.getForObject("https://api.chucknorris.io/jokes/categories", String.class);
        String[] lineas = jokeCategories.split("\\r?\\n");
        List<String> listaCategorias = Arrays.asList(lineas);
        JokeCategories categories = new JokeCategories();
        categories.setCategoriesList(listaCategorias);
        return categories;
    }
}
