package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase WrongLenghtFieldException representa una excepción personalizada para la validación
 * de solicitudes de API. Esta excepción extiende la clase Exception y contiene información adicional
 * como un mensaje de error específico y un código de estado HTTP.
 */
public class WrongLenghtFieldException extends RequestApiValidationException {
    /**
     * @param externalMessage el mensaje de error específico
     */
    private static final String EXTERNAL_MESSAGE = "El campo %s sobrepasa la longitud maxima de entrada.";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    /**
     * Crea una nueva instancia de WrongLenghtFieldException con el mensaje de error y el código
     * de estado HTTP proporcionados.
     *
     * @param args los argumentos utilizados para formatear el mensaje de error
     */
    public WrongLenghtFieldException (String args) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{args});
    }

    /**
     * Crea una nueva instancia de WrongLenghtFieldException con el mensaje de error y el código
     * de estado HTTP proporcionados.
     */
    public WrongLenghtFieldException ( ) {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
