package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase DateIsBeforeException representa una excepción personalizada que se lanza cuando
 * una fecha es anterior a otra fecha en una solicitud. Esta excepción extiende la clase
 * RequestApiValidationException para proporcionar un mensaje específico y un código de estado HTTP.
 */
public class DateIsBeforeException extends RequestApiValidationException {
    private static final String EXTERNAL_MESSAGE = "La %s no puede ser anterior a %s";

    /**
     * Crea una nueva instancia de DateIsBeforeException con los nombres de los campos de fechas involucrados.
     * Esta excepción se lanza cuando la fecha en el campo 'fechaUno' es anterior a la fecha en el campo 'fechaDos'.
     *
     * @param fechaUno nombre del campo que contiene la primera fecha
     * @param fechaDos nombre del campo que contiene la segunda fecha
     */
    public DateIsBeforeException (String fechaUno, String fechaDos,HttpStatus statusCode) {
        super(EXTERNAL_MESSAGE,statusCode, new String[]{fechaUno, fechaDos});
    }
    
}
