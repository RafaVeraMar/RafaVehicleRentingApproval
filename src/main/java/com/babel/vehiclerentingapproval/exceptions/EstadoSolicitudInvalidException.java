package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class EstadoSolicitudInvalidException extends RequestApiValidationException{
    private static final String externalMessage = "El codigo de resolución no es válido";
    private static final HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public EstadoSolicitudInvalidException() {
        super(externalMessage, statusCode);
    }
}
