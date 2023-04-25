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

    public DireccionNotFoundException(HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
