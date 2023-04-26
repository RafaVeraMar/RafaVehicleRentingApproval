package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase InputIsNegativeOrZeroException representa una excepción personalizada que se lanza cuando
 * un valor de entrada es negativo o igual a cero. Esta excepción extiende la clase
 * RequestApiValidationException para proporcionar un mensaje específico y un código de estado HTTP.
 */
public class InputIsNegativeOrZeroException extends ApplicationException {
    private static final String EXTERNAL_MESSAGE = "El campo %s tiene que ser mayor que cero.";

    public InputIsNegativeOrZeroException (String numVehiculos, HttpStatus badRequest) {
        super(EXTERNAL_MESSAGE, badRequest, new String[]{numVehiculos});
    }
}
