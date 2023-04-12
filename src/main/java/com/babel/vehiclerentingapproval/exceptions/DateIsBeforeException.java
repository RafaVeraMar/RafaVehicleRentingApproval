package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class DateIsBeforeException extends RequestApiValidationException {
    private static final String externalMessage = "La %s no puede ser anterior a %s";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public DateIsBeforeException (String fechaUno, String fechaDos) {
        super(externalMessage, statusCode, new String[]{fechaUno, fechaDos});
    }


}
