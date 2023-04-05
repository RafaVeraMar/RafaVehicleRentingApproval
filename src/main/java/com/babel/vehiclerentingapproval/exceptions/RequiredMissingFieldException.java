package com.babel.vehiclerentingapproval.exceptions;


import org.springframework.http.HttpStatus;

public class RequiredMissingFieldException extends RequestApiValidationException {

    private static final String externalMessage = "El nombre de la persona no puede estar vacio";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public RequiredMissingFieldException (String[] args) {
        super(externalMessage, statusCode, args);
    }

    public RequiredMissingFieldException ( ) {
        super(externalMessage, statusCode);
    }
}
