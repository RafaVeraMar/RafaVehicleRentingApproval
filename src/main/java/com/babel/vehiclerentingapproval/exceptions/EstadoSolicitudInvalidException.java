package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta excepción se lanza cuando el codigo de resolución no es válido
 * @see RequestApiValidationException
 * @author javier.serrano@babelgroup.com
 */
public class EstadoSolicitudInvalidException extends RequestApiValidationException{
    private static final String EXTERNALMESSAGE = "El estado de resolución no es válido";
    private static final HttpStatus STATUSCODE = HttpStatus.INTERNAL_SERVER_ERROR;

    public EstadoSolicitudInvalidException() {
        super(EXTERNALMESSAGE, STATUSCODE);
    }
}
