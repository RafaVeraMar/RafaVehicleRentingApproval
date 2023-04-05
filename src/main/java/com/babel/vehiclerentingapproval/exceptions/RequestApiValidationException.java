package com.babel.vehiclerentingapproval.exceptions;

import org.springframework.http.HttpStatus;

public class RequestApiValidationException extends Exception {

    private String externalMessage;
    private HttpStatus statusCode;

    public RequestApiValidationException (String externalMessage, HttpStatus statusCode) {
        this.externalMessage = externalMessage;
        this.statusCode = statusCode;
    }

    public RequestApiValidationException (String externalMessage, HttpStatus statusCode, String[] args) {
        this.externalMessage = String.format(externalMessage, args);
        this.statusCode = statusCode;
    }

    public String getExternalMessage ( ) {
        return externalMessage;
    }

    public HttpStatus getStatusCode ( ) {
        return statusCode;
    }
}
