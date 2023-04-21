package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta excepción se lanza cuando el codigo de resolución es nulo
 * @see RequestApiValidationException
 * @author javier.serrano@babelgroup.com
 */
public class EstadoSolicitudNotFoundException extends RequestApiValidationException{
    private static final String EXTERNALMESSAGE = "No existe ninguna codigo de resolución asociado a esa solicitud";
    private static final HttpStatus STATUSCODE = HttpStatus.NOT_FOUND;
    public EstadoSolicitudNotFoundException() {
        super(EXTERNALMESSAGE, STATUSCODE);
    }
}
