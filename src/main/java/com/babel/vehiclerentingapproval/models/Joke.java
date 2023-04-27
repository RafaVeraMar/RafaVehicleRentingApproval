package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que modela el objeto Joke
 * @author alvaro.dorado@babelgroup.com
 */

public class Joke {
    @Getter    @Setter
    private String joke;

    public Joke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return joke;
    }
}
