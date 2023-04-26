package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Esta clase excepción se lanza como resultado al producirse un error en el envío del email. Extiende de la clase RuntimeException.
 * @see RuntimeException
 * @author andres.guijarro@babelgroup.com
 */
public class FailedSendingEmail extends ApplicationException{
    private static final String EXTERNAL_MESSAGE = "No se pudo enviar desde el correo %s";

    public FailedSendingEmail(HttpStatus statusCode,String email) {
        super(EXTERNAL_MESSAGE, statusCode, new String[]{email});
    }


}
