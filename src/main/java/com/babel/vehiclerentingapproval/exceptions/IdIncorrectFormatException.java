package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class IdIncorrectFormatException extends RequestApiValidationException{
    private static final String EXTERNALMESSAGE = "Error: el formato de ID no es válido";
    private static final HttpStatus STATUSCODE = HttpStatus.BAD_REQUEST;
    public IdIncorrectFormatException() {
        super(EXTERNALMESSAGE, STATUSCODE);
    }
}
