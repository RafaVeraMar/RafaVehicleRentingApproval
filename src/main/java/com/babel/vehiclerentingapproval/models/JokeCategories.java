package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JokeCategories {
    @Getter    @Setter
    private List<String> categoriesList;

    public JokeCategories(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public void setCategoriesList(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public JokeCategories() {
        categoriesList = null;
    }

    @Override
    public String toString() {
        return "JokeCategories{" +
                "categoriesList=" + categoriesList +
                '}';
    }
}
