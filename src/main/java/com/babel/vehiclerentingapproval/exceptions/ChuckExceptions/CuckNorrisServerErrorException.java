package com.babel.vehiclerentingapproval.exceptions.ChuckExceptions;

import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;

public class CuckNorrisServerErrorException extends ChuckNorrisException{
    /**
     * Esta clase excepción se lanza como resultado de la validacion de los datos
     * de la base de datos cuando el DNI ha sido encontrado en la base de datos.
     * Extiende de la clase RuntimeException.
     *
     * @param message
     * @param statusCode
     * @param statusText
     * @param responseHeaders
     * @param responseBody
     * @param responseCharset
     * @author andres.guijarro@babelgroup.com
     * @see RuntimeException
     */
    private static final String EXTERNAL_MESSAGE = "Error de servidor en la API externa";

    public CuckNorrisServerErrorException(String msg) {
        super(msg);
    }
    public CuckNorrisServerErrorException() {
        super(EXTERNAL_MESSAGE);
    }
    public CuckNorrisServerErrorException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public String CuckNorrisServerErrorExceptionManual(String EXTERNAL_MESSAGE){
        return EXTERNAL_MESSAGE;
    }
}
