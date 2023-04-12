package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class PersonaNotFoundException extends RequestApiValidationException {
    private static final String externalMessage = "La persona con id: %s no existe.";
    //private static final String externalMessageNoId = "Persona no encontrada";

    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;


    public PersonaNotFoundException (int personaId) {
        super(externalMessage, statusCode, new String[]{String.valueOf(personaId)});
    }

    public PersonaNotFoundException ( ) {
        super(externalMessage, statusCode);

    }

    /*public PersonaNotFoundException ( ) {
        super(externalMessageNoId, statusCode);

    }*/
}
