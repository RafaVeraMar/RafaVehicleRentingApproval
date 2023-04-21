package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase InputIsNullOrIsEmpty representa una excepción personalizada que se lanza cuando
 * un valor de entrada es nulo o vacío. Esta excepción extiende la clase
 * RequestApiValidationException para proporcionar un mensaje específico y un código de estado HTTP.
 */
public class InputIsNullOrIsEmpty extends RequestApiValidationException {

    private static final String EXTERNAL_MESSAGE = "El campo %s no puede ser nulo o estar vacio.";

    public InputIsNullOrIsEmpty (String numVehivulos, HttpStatus badRequest) {
        super(EXTERNAL_MESSAGE, badRequest, new String[]{numVehivulos});
    }
}
