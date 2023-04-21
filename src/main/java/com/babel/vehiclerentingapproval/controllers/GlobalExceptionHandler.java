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

    @ExceptionHandler({RequiredMissingFieldException.class, WrongLenghtFieldException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.BAD_REQUEST);
        respuesta.put(DESCRIPCION, "Comprueba los datos de entrada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(DireccionNotFoundException.class)
    public ResponseEntity<Object> handleDireccionNotFoundException(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.BAD_REQUEST);
        respuesta.put(DESCRIPCION, "Direccion no encontrada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(DniFoundException.class)
    public ResponseEntity<Object> handleDniFoundException(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.BAD_REQUEST);
        respuesta.put(DESCRIPCION, "El NIF de la persona ya existe en la base de datos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<Object> handlePersonaNotFoundException(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.NOT_FOUND);
        respuesta.put(DESCRIPCION, "Persona no encontrada en la base de datos");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException (Exception e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
        respuesta.put(DESCRIPCION, "Error: Ha ocurrido un error interno en el servidor");
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
