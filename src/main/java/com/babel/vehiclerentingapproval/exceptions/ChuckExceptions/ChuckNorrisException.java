package com.babel.vehiclerentingapproval.exceptions.ChuckExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.Charset;

public class ChuckNorrisException extends RestClientException {

    /**
     * Esta clase excepción se lanza como resultado de la validacion de los datos
     * de la base de datos cuando el DNI ha sido encontrado en la base de datos.
     * Extiende de la clase RuntimeException.
     * @see RuntimeException
     *
     * @author andres.guijarro@babelgroup.com
     */
    private final String EXTERNAL_MESSAGE=null;


    public ChuckNorrisException(String msg) {
        super(msg);
    }

    public ChuckNorrisException(String msg, Throwable ex) {
        super(msg, ex);
    }
}


