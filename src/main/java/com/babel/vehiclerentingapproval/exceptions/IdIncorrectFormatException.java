package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta excepción se lanza cuando no se puede parsear la id proporcionada
 * @see RequestApiValidationException
 * @author javier.serrano@babelgroup.com
 */
public class IdIncorrectFormatException extends ApplicationException{
    private static final String EXTERNALMESSAGE = "Error: el formato de ID no es válido";

    public IdIncorrectFormatException(HttpStatus statusCode) {
        super(EXTERNALMESSAGE, statusCode);
    }
}
