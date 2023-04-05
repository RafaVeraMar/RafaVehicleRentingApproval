package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class InputIsNegativeOrZeroException extends RequestApiValidationException {
    private static final String externalMessage = "El campo %s tiene que ser mayor que uno.";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public InputIsNegativeOrZeroException (String args) {
        super(externalMessage, statusCode, new String[]{args});
    }

    public InputIsNegativeOrZeroException ( ) {
        super(externalMessage, statusCode);
    }
}
