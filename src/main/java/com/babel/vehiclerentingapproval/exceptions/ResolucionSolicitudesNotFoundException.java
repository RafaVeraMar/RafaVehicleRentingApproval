package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción se lanza como resultado de la validacion de los datos
 * de la base de datos cuando no se encuentra ninguna resolución de solicitudes. extiende de la clase RuntimeException.
 * @see RuntimeException
 * @author andres.guijarro@babelgroup.com
 */
public class ResolucionSolicitudesNotFoundException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "No existe resolución de la solicitud buscada";

    public ResolucionSolicitudesNotFoundException(HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
