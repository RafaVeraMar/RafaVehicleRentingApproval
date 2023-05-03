package com.babel.vehiclerentingapproval.exceptions.ChuckExceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.Charset;

public class CuckNorrisServerErrorException extends ChuckNorrisException {
    private static final String EXTERNAL_MESSAGE = "Error de servidor en la API externa: server unavailable";

    public CuckNorrisServerErrorException(String msg) {
        super(msg);
    }
    public CuckNorrisServerErrorException() {
        super(EXTERNAL_MESSAGE);
    }

    public String CuckNorrisServerErrorExceptionManual(String EXTERNAL_MESSAGE){
        return EXTERNAL_MESSAGE;
    }
}
