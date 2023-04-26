package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta excepción se lanza cuando el codigo de resolución es nulo
 * @see RequestApiValidationException
 * @author javier.serrano@babelgroup.com
 */
public class EstadoSolicitudNotFoundException extends ApplicationException{
    private static final String EXTERNALMESSAGE = "No se encuentra estado el asociado";
    public EstadoSolicitudNotFoundException(HttpStatus statusCode) {
        super(EXTERNALMESSAGE, statusCode);
    }
}
