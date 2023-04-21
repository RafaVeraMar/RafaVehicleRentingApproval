package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción se lanza como resultado de la validacion de los datos
 * de la base de datos cuando no se encuentra una direccion. Extiende de la clase Exception
 *
 * @author andres.guijarro@babelgroup.com
 * @see RuntimeException
 */
public class DireccionNotFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "No se encuentra dirección buscada";
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public DireccionNotFoundException() {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
