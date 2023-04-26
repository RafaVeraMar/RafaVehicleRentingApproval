package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase EmailNotSentException representa una excepción personalizada que se lanza cuando
 * no se ha podido enviar el Email. Esta excepción extiende la clase
 * {@link ApplicationNotificationException} para proporcionar un mensaje específico y un código de estado HTTP.
 * @see ApplicationNotificationException
 */
public class EmailNotSentException extends ApplicationNotificationException{

    private final String externalMessage;
    private final HttpStatus statusCode;

    /**
     * Crea una nueva instancia de  EmailNotSentException con el mensaje de error y el código
     * de estado HTTP proporcionados.
     *
     * @param externalMessage el mensaje de error específico
     * @param statusCode      el código de estado HTTP asociado
     */
    public EmailNotSentException (String externalMessage, HttpStatus statusCode) {
        super(externalMessage,statusCode);
        this.externalMessage = externalMessage;
        this.statusCode = statusCode;
    }

    /**
     * Crea una nueva instancia de EmailNotSentException con el mensaje de error formateado,
     * el código de estado HTTP y los argumentos proporcionados para formatear el mensaje de error.
     *
     * @param externalMessage el mensaje de error específico
     * @param statusCode      el código de estado HTTP asociado
     * @param args            los argumentos utilizados para formatear el mensaje de error
     */
    public EmailNotSentException (String externalMessage, HttpStatus statusCode, String[] args) {
        super(externalMessage,statusCode,args);
        this.externalMessage = String.format(externalMessage, args);
        this.statusCode = statusCode;
    }

    /**
     * Obtiene el mensaje de error específico.
     *
     * @return el mensaje de error
     */
    @Override
    public String getExternalMessage ( ) {
        return externalMessage;
    }

    /**
     * Obtiene el código de estado HTTP asociado.
     *
     * @return el código de estado HTTP
     */
    @Override
    public HttpStatus getStatusCode ( ) {
        return statusCode;
    }
}
