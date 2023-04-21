package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;


    @BeforeEach
    void setupAll() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleRequestApiValidationException() {
        RequestApiValidationException e = new RequestApiValidationException("message", HttpStatus.BAD_REQUEST, "externalMessage");
        ResponseEntity<Object> response = globalExceptionHandler.handleRequestApiValidationException(e);
        Map<String, Object> expected = new HashMap<>();
        expected.put("status", HttpStatus.BAD_REQUEST);
        expected.put("Descripcion: ", "externalMessage");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

}
