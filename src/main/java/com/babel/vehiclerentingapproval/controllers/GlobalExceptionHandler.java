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
        respuesta.put("status", e.getStatusCode());
        respuesta.put("descripcion", e.getExternalMessage());
        return new ResponseEntity<>(respuesta, e.getStatusCode());
    }

    @ExceptionHandler(SolicitudRentingNotFoundException.class)
    @ResponseBody
    public Throwable handleSolicitudRentingNotFoundException (SolicitudRentingNotFoundException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.NOT_FOUND);
        respuesta.put("descripcion", "Error: No se encuentra la solicitud buscada, inténtelo más tarde");
        return e;
    }

    @ExceptionHandler(EstadoSolicitudNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEstadoSolicitudNotFoundException (EstadoSolicitudNotFoundException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.NOT_FOUND);
        respuesta.put("descripcion", "Error: Estado de solicitud no válido");
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNumberFormatException (NumberFormatException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.BAD_REQUEST);
        respuesta.put("descripcion", "Error: el formato de ID es inválido");
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException (Exception e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        respuesta.put("descripcion", "Error: Ha ocurrido un error interno en el servidor");
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProfesionNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleProfesionNotFoundException (ProfesionNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS, HttpStatus.BAD_REQUEST);
        map.put(DESCRIPCION, "Profesion no encontrada en la base de datos");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonaNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handlePersonaNotFoundException (PersonaNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS, HttpStatus.NOT_FOUND);
        map.put(DESCRIPCION, "Persona no encontrada en la base de datos");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RentaFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRentaFoundException (RentaFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS, HttpStatus.NOT_ACCEPTABLE);
        map.put(DESCRIPCION, "La renta ya existe en la base de datos");
        return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    }
}
