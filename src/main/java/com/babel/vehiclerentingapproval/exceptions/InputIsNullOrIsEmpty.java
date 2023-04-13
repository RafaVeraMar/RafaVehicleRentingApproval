package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase InputIsNullOrIsEmpty representa una excepción personalizada que se lanza cuando
 * un valor de entrada es nulo o vacío. Esta excepción extiende la clase
 * RequestApiValidationException para proporcionar un mensaje específico y un código de estado HTTP.
 */
public class InputIsNullOrIsEmpty extends RequestApiValidationException {

    private static final String externalMessage = "El campo %s no puede ser nulo o estar vacio.";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    /**
     * Crea una nueva instancia de InputIsNullOrIsEmpty con el nombre del campo
     * que contiene el valor nulo o vacío.
     *
     * @param args nombre del campo que contiene el valor nulo o vacío
     */
    public InputIsNullOrIsEmpty (String args) {
        super(externalMessage, statusCode, new String[]{args});
    }
}
