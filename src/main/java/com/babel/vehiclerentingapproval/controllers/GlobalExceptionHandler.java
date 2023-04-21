package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String DESCRIPCION = "Descripcion: ";
    private static final String STATUS = "status: ";
    @ExceptionHandler(RequestApiValidationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRequestApiValidationException (RequestApiValidationException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, e.getStatusCode());
        respuesta.put(DESCRIPCION, e.getExternalMessage());
        return new ResponseEntity<>(respuesta, e.getStatusCode());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException (Exception e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
        respuesta.put(DESCRIPCION, "Error: Ha ocurrido un error interno en el servidor");
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
