package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class EstadoSolicitudNotFoundException extends RequestApiValidationException{
    private static final String externalMessage = "No existe ninguna codigo de resolución asociado a esa solicitud";
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;
    public EstadoSolicitudNotFoundException() {
        super(externalMessage, statusCode);
    }
}
