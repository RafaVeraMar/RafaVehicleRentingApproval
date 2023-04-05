package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class ProfesionNotFoundException extends RequestApiValidationException {

    private static final String externalMessage = "La profesion %s no existe";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public ProfesionNotFoundException (int profesionId) {
        super(externalMessage, statusCode, new String[]{String.valueOf(profesionId)});
    }

    public ProfesionNotFoundException ( ) {
        super(externalMessage, statusCode);
    }
}
