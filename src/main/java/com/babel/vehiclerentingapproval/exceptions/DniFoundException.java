package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción se lanza como resultado de la validacion de los datos
 * de la base de datos cuando el DNI ha sido encontrado en la base de datos.
 * Extiende de la clase RuntimeException.
 * @see RuntimeException
 *
 * @author andres.guijarro@babelgroup.com
 */
public class DniFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "No se encuentra dni";
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public DniFoundException() {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
