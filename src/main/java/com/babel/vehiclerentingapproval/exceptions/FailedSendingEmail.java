package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción se lanza como resultado al producirse un error en el envío del email. Extiende de la clase RuntimeException.
 * @see RuntimeException
 * @author andres.guijarro@babelgroup.com
 */
public class FailedSendingEmail extends RequestApiValidationException{
    private static final String EXTERNAL_MESSAGE = "No se pudo enviar desde el correo %s";
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public FailedSendingEmail(String email) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{email});
    }


}
