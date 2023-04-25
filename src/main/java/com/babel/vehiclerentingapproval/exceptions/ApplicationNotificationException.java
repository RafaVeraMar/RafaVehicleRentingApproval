package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

/**
 * La clase ApplicationNotificationException representa una excepción personalizada para el manejo de errores de la aplicación.
 * Esta excepción es padre del resto de excepciones personalizadas encargadas del manejo de errores.
 * Extiende la clase ApplicationException y contiene información adicional
 * como un mensaje de error específico y un código de estado HTTP.
 * @see ApplicationException
 */
public class ApplicationNotificationException extends ApplicationException {

    private final String externalMessage;
    private final HttpStatus statusCode;

    /**
     * Crea una nueva instancia de ApplicationNotificationException con el mensaje de error y el código
     * de estado HTTP proporcionados.
     *
     * @param externalMessage el mensaje de error específico
     * @param statusCode      el código de estado HTTP asociado
     */
    public ApplicationNotificationException (String externalMessage, HttpStatus statusCode) {
        super(externalMessage,statusCode);
        this.externalMessage = externalMessage;
        this.statusCode = statusCode;
    }

    /**
     * Crea una nueva instancia de ApplicationNotificationException con el mensaje de error formateado,
     * el código de estado HTTP y los argumentos proporcionados para formatear el mensaje de error.
     *
     * @param externalMessage el mensaje de error específico
     * @param statusCode      el código de estado HTTP asociado
     * @param args            los argumentos utilizados para formatear el mensaje de error
     */
    public ApplicationNotificationException (String externalMessage, HttpStatus statusCode, String[] args) {
        super(externalMessage,statusCode,args);
        this.externalMessage = String.format(externalMessage, args);
        this.statusCode = statusCode;
    }

    /**
     * Obtiene el mensaje de error específico.
     *
     * @return el mensaje de error
     */
    public String getExternalMessage ( ) {
        return externalMessage;
    }

    /**
     * Obtiene el código de estado HTTP asociado.
     *
     * @return el código de estado HTTP
     */
    public HttpStatus getStatusCode ( ) {
        return statusCode;
    }
}
