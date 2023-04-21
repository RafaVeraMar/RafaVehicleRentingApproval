package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción devuelve NOT_FOUND cuando el ID no ha sido encontrado
 *
 * @author alvaro.aleman@babelgroup.com, ramon.vazquez@babelgroup.com
 */
public class SolicitudRentingNotFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "ID de solicitud no encontrado";

    public SolicitudRentingNotFoundException(HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode);
    }

}
