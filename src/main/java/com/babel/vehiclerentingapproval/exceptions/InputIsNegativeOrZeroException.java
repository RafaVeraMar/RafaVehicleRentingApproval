package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase InputIsNegativeOrZeroException representa una excepción personalizada que se lanza cuando
 * un valor de entrada es negativo o igual a cero. Esta excepción extiende la clase
 * RequestApiValidationException para proporcionar un mensaje específico y un código de estado HTTP.
 */
public class InputIsNegativeOrZeroException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "El campo %s tiene que ser mayor que cero.";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    /**
     * Crea una nueva instancia de InputIsNegativeOrZeroException con el nombre del campo
     * que contiene el valor negativo o igual a cero.
     *
     * @param args nombre del campo que contiene el valor negativo o igual a cero
     */
    public InputIsNegativeOrZeroException (String args) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{args});
    }

}
