package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;
import com.babel.vehiclerentingapproval.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="Operaciones con persona",description = "Endpoint para operar con una persona en la base de datos a partir de unos datos de entrada.")
@RestController
public class PersonaController {

    PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/persona")
    @Operation(summary = "Añadir una persona", description = "Añade una persona a la base de datos")
    @ApiResponses( value = { @ApiResponse( responseCode = "200", description = "La persona se ha añadido con éxito a la base de datos.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Comprueba los datos de entrada.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = { @Content( mediaType = "application/json")})
    })

    ResponseEntity addPersona(@RequestBody Persona persona){
        Persona personaCreada;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            personaCreada=this.personaService.addPersona(persona);
        } catch (RequiredMissingFieldException e) {
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("descripcion", "Comprueba los datos de entrada");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        } catch (WrongLenghtFieldException e) {
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("descripcion", "Comprueba los datos de entrada");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }catch (DireccionNotFoundException e){
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("descripcion", "Direccion no encontrada");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }catch (DniFoundException e){
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("descripcion", "El NIF de la persona ya existe en la base de datos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("description", "Error del sistema");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }

        map.put("status", HttpStatus.OK);
        map.put("description", "Persona añadida.");
        map.put("id", personaCreada.getPersonaId());
        return ResponseEntity.ok(personaCreada);
    }

    @GetMapping("/persona/{id}/productosContratados")
    @Operation(summary = "Listar los productos contratados por una persona", description = "Devuelve los productos contratados por una persona dada su ID")
    @ApiResponses( value = {@ApiResponse(responseCode = "200", description = "Se han obtenido con éxito los productos contratados por una persona.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "La persona no existe.", content = { @Content( mediaType = "application/json")}),
    })
    ResponseEntity viewPersonaProducto(@PathVariable("id") int id){
        List<ProductoContratado> lista;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            lista = this.personaService.viewPersonaProducto(id);
            map.put("status", HttpStatus.OK);
            map.put("description", "Productos contratados por una persona.");
            map.put("Lista de productos contratados por una persona",lista);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        } catch (PersonaNotFoundException e) {
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("description", "Persona no encontrada en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }


    @ApiResponses( value = { @ApiResponse( responseCode = "200", description = "Los datos de la persona se han modificado correctamente.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "La persona que se ha intentado modificar no se ha encontrado en la base de datos.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "La direccion proporcionada no se encuentra en la base de datos.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor. No se ha podido realizar la operación.", content = { @Content( mediaType = "application/json")})})
    @Parameter(name = "persona", description = "JSON de la persona con datos a modificar", required = true)
    @Operation(summary = "Modificar los datos de una persona", description = "Modifica los datos de una persona en la base de datos.")
    @PutMapping("/persona/{id}")
    ResponseEntity modificarPersona(@RequestBody Persona persona,@PathVariable int id){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            persona.setPersonaId(id);
            this.personaService.modificarPersona(persona);
            map.put("status", HttpStatus.OK);
            map.put("descripcion", "La persona se ha modificado correctamente en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }catch(PersonaNotFoundException e){
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("descripcion", "La persona que se intenta modificar no existe en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        } catch (DireccionNotFoundException e) {
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("descripcion", "La direccion no existe en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
