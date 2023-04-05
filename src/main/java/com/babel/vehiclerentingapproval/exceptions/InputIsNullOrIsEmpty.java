package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class InputIsNullOrIsEmpty extends RequestApiValidationException {

    private static final String externalMessage = "El campo %s no puede ser nulo o estar vacio.";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public InputIsNullOrIsEmpty (String args) {
        super(externalMessage, statusCode, new String[]{args});
    }

    public InputIsNullOrIsEmpty ( ) {
        super(externalMessage, statusCode);
    }
}
