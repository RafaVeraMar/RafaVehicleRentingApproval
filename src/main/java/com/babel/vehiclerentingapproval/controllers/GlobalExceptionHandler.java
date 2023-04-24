package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
/**
 * Manejador global de excepciones.
 */


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String DESCRIPCION = "Descripcion: ";
    private static final String STATUS = "status: ";


    /**
     * Metodo que maneja todas las excepciones personalizadas, ya que RequestApiValidationException es la excepción padre de la que
     * heredan el resto de excepciones creadas.
     * <p>
     *
     * @param e Excepción que hereda de RequestApiValidationException
     * @return un objeto ResponseEntity que contiene la respuesta y el código de error de la excepción lanzada
     * @see RequestApiValidationException
     */
    @ExceptionHandler(RequestApiValidationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRequestApiValidationException (RequestApiValidationException e) {
        return new ResponseEntity<>(Map.of(DESCRIPCION, e.getExternalMessage()), e.getStatusCode());
    }

    /**
     * Metodo que maneja el resto de excepciones que puedan generarse que no sean excepciones personalizadas creadas
     * para esta aplicación.
     * <p>
     *
     * @param e Excepción genérica
     * @return un objeto ResponseEntity que contiene una descripción y estado de error interno del servidor.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException (Exception e) {
        return new ResponseEntity<>(Map.of(DESCRIPCION, "Error: Ha ocurrido un error interno en el servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
