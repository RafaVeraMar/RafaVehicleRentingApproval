package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class PersonByEmailNotFoundException extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "No se ha encontrado ninguna persona con el email %s";

    public PersonByEmailNotFoundException (HttpStatus statusCode, String email){
        super(EXTERNAL_MESSAGE, statusCode);
    }

}
