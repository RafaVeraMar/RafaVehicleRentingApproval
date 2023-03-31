package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;
import com.babel.vehiclerentingapproval.services.PersonaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonaController {

    PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/persona")
    ResponseEntity addPersona(@RequestBody Persona persona){
        try {
            this.personaService.addPersona(persona);
        } catch (RequiredMissingFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comprueba los datos de entrada");
        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(persona);
    }

    @GetMapping("/mostrarProductosPersona/{id}")
    @ApiResponses( value = {@ApiResponse(responseCode = "200", description = "Se han obtenido con éxito los productos contratados por una persona.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "La persona no existe.", content = { @Content( mediaType = "application/json")}),
    })
    ResponseEntity viewPersonaProducto(@PathVariable("id") int id){
        List<ProductoContratado> lista;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            lista = this.personaService.viewPersonaProducto(id);
        } catch (PersonaNotFoundException e) {
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("description", "Persona no encontrada en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        map.put("status", HttpStatus.OK);
        map.put("description", "Productos contratados por una persona.");
        map.put("Lista de productos contratados por una persona",lista);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
}
