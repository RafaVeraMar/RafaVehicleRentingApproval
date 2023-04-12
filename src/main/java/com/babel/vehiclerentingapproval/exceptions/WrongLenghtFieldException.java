package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class WrongLenghtFieldException extends RequestApiValidationException {

    private static final String externalMessage = "El campo %s sobrepasa la longitud maxima de entrada.";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public WrongLenghtFieldException (String args) {
        super(externalMessage, statusCode, new String[]{args});
    }

    public WrongLenghtFieldException ( ) {
        super(externalMessage, statusCode);
    }
}
