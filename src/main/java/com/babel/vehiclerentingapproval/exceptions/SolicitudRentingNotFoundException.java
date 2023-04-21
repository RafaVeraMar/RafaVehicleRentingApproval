package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción devuelve NOT_FOUND cuando el ID no ha sido encontrado
 *
 * @author alvaro.aleman@babelgroup.com, ramon.vazquez@babelgroup.com
 */
public class SolicitudRentingNotFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "ID de solicitud no encontrado";
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public SolicitudRentingNotFoundException() {
        super(EXTERNAL_MESSAGE, statusCode);
    }

}
