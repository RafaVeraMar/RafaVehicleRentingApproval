package com.babel.vehiclerentingapproval.exceptions;


import org.springframework.http.HttpStatus;

/**
 * La clase RequiredMissingFieldException representa una excepción personalizada para campos
 * requeridos que están vacíos o faltantes en una solicitud de API. Esta excepción extiende
 * la clase RequestApiValidationException y contiene información adicional, como un mensaje
 * de error específico y un código de estado HTTP.
 */
public class RequiredMissingFieldException extends RequestApiValidationException {

    private static final String EXTERNAL_MESSAGE = "El nombre de la persona no puede estar vacio";

    /**
     * Crea una nueva instancia de RequiredMissingFieldException con el mensaje de error y el
     * código de estado HTTP predeterminados.
     */
    public RequiredMissingFieldException (HttpStatus statusCode ) {
        super(EXTERNAL_MESSAGE, statusCode);
    }
}
