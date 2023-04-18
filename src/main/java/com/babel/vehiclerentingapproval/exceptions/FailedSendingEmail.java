package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class FailedSendingEmail extends RequestApiValidationException{
    private static final String EXTERNAL_MESSAGE = "No se pudo enviar el correo %s desde %s";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public FailedSendingEmail(Integer idPersona,String email) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{email,String.valueOf(idPersona)});
    }


}
