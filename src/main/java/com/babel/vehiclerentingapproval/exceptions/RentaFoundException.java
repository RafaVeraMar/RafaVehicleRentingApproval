package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción se lanza como resultado de la validacion de los datos
 * de la base de datos cuando la renta ha sido encontrada en la base de datos. extiende de la clase RuntimeException.
 * @see RuntimeException
 * @author andres.guijarro@babelgroup.com
 */
public class RentaFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "No se encuentra la renta solicitada";
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public RentaFoundException() {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
