package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class FailedSendingEmail extends RequestApiValidationException{
    private static final String EXTERNAL_MESSAGE = "No se pudo enviar desde el correo %s";

    public FailedSendingEmail(HttpStatus statusCode,String email) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{email});
    }


}
