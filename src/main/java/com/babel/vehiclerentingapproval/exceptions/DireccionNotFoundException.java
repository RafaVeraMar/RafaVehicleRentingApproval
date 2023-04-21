package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción es un esquema vacío básico para ser lanzado desde los puntos
 * requeridos de la aplicación. Su lanzamiento es resultado de la validacion de los datos
 * de la base de datos. Extiende de la clase Exception
 *
 * @author andres.guijarro@babelgroup.com
 * @see Exception
 */
public class DireccionNotFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "No se encuentra dirección buscada";
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public DireccionNotFoundException() {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
