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
    private static final String DESCRIPCION = "descripcion";
    private static final String STATUS = "status";

    @ExceptionHandler(RequestApiValidationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRequestApiValidationException (RequestApiValidationException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, e.getStatusCode());
        respuesta.put(DESCRIPCION, e.getExternalMessage());
        return new ResponseEntity<>(respuesta, e.getStatusCode());
    }

    @ExceptionHandler(SolicitudRentingNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleSolicitudRentingNotFoundException (SolicitudRentingNotFoundException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.NOT_FOUND);
        respuesta.put(DESCRIPCION, "Error: No se encuentra la solicitud buscada, inténtelo más tarde");
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstadoSolicitudNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEstadoSolicitudNotFoundException (EstadoSolicitudNotFoundException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.NOT_FOUND);
        respuesta.put(DESCRIPCION, "Error: Estado de solicitud no válido");
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNumberFormatException (NumberFormatException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.BAD_REQUEST);
        respuesta.put(DESCRIPCION, "Error: el formato de ID es inválido");
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DireccionNotFoundException.class)
    public ResponseEntity<Object> handleDireccionNotFoundException (Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.BAD_REQUEST);
        respuesta.put(DESCRIPCION, "Direccion no encontrada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(DniFoundException.class)
    public ResponseEntity<Object> handleDniFoundException (Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.BAD_REQUEST);
        respuesta.put(DESCRIPCION, "El NIF de la persona ya existe en la base de datos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
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
