package com.babel.vehiclerentingapproval.models;
/**
 * Clase que modela el objeto Joke
 * @author alvaro.dorado@babelgroup.com
 */

public class Joke {
    private String joke;

    public Joke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return joke;
    }
}
