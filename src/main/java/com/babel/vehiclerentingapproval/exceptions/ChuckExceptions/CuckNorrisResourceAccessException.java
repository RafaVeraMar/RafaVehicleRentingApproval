package com.babel.vehiclerentingapproval.exceptions.ChuckExceptions;

public class CuckNorrisResourceAccessException extends ChuckNorrisException{
    private static final String EXTERNAL_MESSAGE = "Error de servidor en la API externa: handshake failed";

    public CuckNorrisResourceAccessException(String msg) {
        super(msg);
    }
    public CuckNorrisResourceAccessException() {
        super(EXTERNAL_MESSAGE);
    }
}
