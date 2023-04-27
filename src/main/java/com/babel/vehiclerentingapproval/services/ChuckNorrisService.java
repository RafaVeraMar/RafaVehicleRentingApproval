package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.JokeCategories;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta interfaz va a definir el servicio de la API externa de Chuck Norris. Los métodos utilizados permiten
 * la comunicación con la API
 */
@Service
public interface ChuckNorrisService {
    public String getRandomJoke();
    public JokeCategories getJokeCategoryList();

}
