package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;


/**
 * La clase ProfesionNotFoundException representa una excepción personalizada que se lanza cuando
 * no se encuentra una profesión con el ID proporcionado. Esta excepción extiende la clase
 * RequestApiValidationException para proporcionar un mensaje específico y un código de estado HTTP.
 */
public class ProfesionNotFoundException extends RequestApiValidationException {

    private static final String externalMessage = "La profesion %s no existe";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    /**
     * Crea una nueva instancia de ProfesionNotFoundException con el ID de la profesión no encontrada.
     *
     * @param profesionId ID de la profesión que no se encuentra
     */
    public ProfesionNotFoundException (int profesionId) {
        super(externalMessage, statusCode, new String[]{String.valueOf(profesionId)});
    }

    /**
     * Crea una nueva instancia de ProfesionNotFoundException sin especificar el ID de la profesión.
     * Utilice este constructor cuando no se requiera información adicional sobre el ID de la profesión no encontrada.
     */
    public ProfesionNotFoundException ( ) {
        super(externalMessage, statusCode);
    }
}
