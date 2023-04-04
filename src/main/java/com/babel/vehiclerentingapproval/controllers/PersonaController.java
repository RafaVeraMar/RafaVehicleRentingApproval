package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.services.PersonaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(String.format("Persona añadida. Id: %d", persona.getPersonaId()));
    }

    @Tag(name="Modificar datos persona",description = "Endpoint que permite modificar los datos de una persona existente en la base de datos.")
    @ApiResponses( value = { @ApiResponse( responseCode = "200", description = "Los datos de la persona se han modificado correctamente.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "La persona que se ha intentado modificar no se ha encontrado en la base de datos.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor. No se ha podido realizar la operación.", content = { @Content( mediaType = "application/json")})})
    @Parameter(name = "persona", description = "JSON de la persona con datos a modificar", required = true)

    @PostMapping("/modificarPersona")
    ResponseEntity modificarPersona(@RequestBody Persona persona){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            this.personaService.modificarPersona(persona);
            map.put("status", HttpStatus.OK);
            map.put("descripcion", "La persona se ha modificado correctamente en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }catch(PersonaNotFoundException e){
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("descripcion", "La persona que se intenta modificar no existe en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("descripcion", "Ha ocurrido un error interno del servidor. La operación no se ha podido realizar.");
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
