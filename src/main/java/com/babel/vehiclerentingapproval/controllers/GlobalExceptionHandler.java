package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

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
    public ResponseEntity<Object> handleSolicitudRentingNotFoundException (SolicitudRentingNotFoundException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.NOT_FOUND);
        respuesta.put("descripcion", "Error: No se encuentra la solicitud buscada, inténtelo más tarde");
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(ResolucionSolicitudesNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleResolucionSolicitudesNotFoundException (ResolucionSolicitudesNotFoundException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.NOT_FOUND);
        respuesta.put("descripcion", "No existen elementos en la base de datos");

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

}
