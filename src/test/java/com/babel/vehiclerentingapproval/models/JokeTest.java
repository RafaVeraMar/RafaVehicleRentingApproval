package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que modela el objeto Joke
 * @author alvaro.dorado@babelgroup.com
 */

public class JokeTest {

    private Joke joke;
    @BeforeEach
    void setupAll() {

    }
    @Test
    void joke_shouldReturn_String(){
         this.joke = new Joke("JOKE");
        Assertions.assertEquals("JOKE",joke.getJoke());
    }

}
