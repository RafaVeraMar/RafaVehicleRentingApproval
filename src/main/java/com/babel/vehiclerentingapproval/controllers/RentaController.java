package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RentaFoundException;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.services.RentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name="Añadir renta",description = "Endpoint que dado un ID de persona y una renta (ambos en JSON) añade la renta a esa persona a la base de datos.")
@RestController
public class RentaController {

    RentaService rentaService;

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }


    @PostMapping("/renta")
    @ApiResponses( value = { @ApiResponse( responseCode = "200", description = "La renta ha añadido con éxito a la base de datos.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "La profesión no existe." , content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "La persona no existe.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "406", description ="Se ha intendado introducir un ID de renta que ya existe.", content = { @Content( mediaType = "application/json")})
    })
    @Parameter(name = "personaId", description = "ID de una persona existente en la base de datos.", required = true)
    @Parameter(name= "renta", description = "Objeto Renta a insertar",required = true)

    ResponseEntity addRenta(@RequestBody Renta renta){
        Renta rentaActualizada;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            rentaActualizada=this.rentaService.addRenta(renta);
        } catch (ProfesionNotFoundException e) {


            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("descripcion", "Profesion no encontrada en la base de datos");
            return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);

        } catch (PersonaNotFoundException e) {


            map.put("status", HttpStatus.NOT_FOUND);
            map.put("description", "Persona no encontrada en la base de datos");
            return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);


        } catch (RentaFoundException e) {

            map.put("status", HttpStatus.NOT_ACCEPTABLE);
            map.put("description", "La renta ya existe en la base de datos");
            return new ResponseEntity<Object>(map,HttpStatus.NOT_ACCEPTABLE);

        }

        map.put("status", HttpStatus.OK);
        map.put("description", "Renta añadida.");
        map.put("id",rentaActualizada.getRentaId());
        return new ResponseEntity<Object>(map,HttpStatus.OK);

    }
}
