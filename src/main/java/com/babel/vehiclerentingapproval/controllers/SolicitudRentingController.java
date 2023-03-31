package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/solicitud")
public class SolicitudRentingController {


    private final SolicitudRentingService solicitud;

    public SolicitudRentingController (SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }

    @PostMapping("")
    @Operation(summary = "Introducir una nueva solicitud de renting", description = "Devuelve el id de la solicitud si se ha introducido correctamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "La persona asociada a la solicitud no existe.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Los datos de entrada sobrepasan la longitud maxima.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Alguno de los datos son nulos o no se han rellenado.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "La fecha de inici de vigor, no puede ser anterior a la fecha de resolucion", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity addSolicitudRenting(@RequestBody SolicitudRenting solicitudRenting){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            solicitud.addSolicitudRenting(solicitudRenting);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "Persona introducida correctamente");
            return new ResponseEntity<Object>(respuesta,HttpStatus.OK);
        } catch (PersonaNotFoundException e) {
            respuesta.put("Status", HttpStatus.BAD_REQUEST);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "La persona no existe en la base de datos.");
            return new ResponseEntity<Object>(respuesta,HttpStatus.BAD_REQUEST);
        } catch (WrongLenghtFieldException e) {
            respuesta.put("Status", HttpStatus.BAD_REQUEST);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "Comprueba que no sobrepase la longitud de los datos de entrada.");
            return new ResponseEntity<Object>(respuesta,HttpStatus.BAD_REQUEST);
        } catch (InputIsNullOrIsEmpty e){
            respuesta.put("Status", HttpStatus.BAD_REQUEST);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "Comprueba los datos de entrada.");
            return new ResponseEntity<Object>(respuesta,HttpStatus.BAD_REQUEST);
        } catch (DateIsBeforeException e) {
            respuesta.put("Status", HttpStatus.BAD_REQUEST);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "La fecha de inicio vigor no puede ser anterior a la de resolucion.");
            return new ResponseEntity<Object>(respuesta,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/estado/{id}")
    ResponseEntity<String> verEstadoSolicitud(@PathVariable String id) throws EstadoSolicitudNotFoundException {

        try{
            int idSolicitud = Integer.parseInt(id);
            String estado = this.solicitud.verEstadoSolicitud(idSolicitud);
            return ResponseEntity.ok(estado);
        }
        catch (EstadoSolicitudNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No existe ninguna codigo de resolución válido asociado a esa solicitud ");

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Debe de pasar un número como id ");
        }
    }
    @GetMapping("{id}")
    ResponseEntity muestraSolicitudPorId(@PathVariable int id){
       try{
           this.solicitud.getSolicitudById(id);
       }catch (SolicitudRentingNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de solicitud no es válido");
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
       return ResponseEntity.ok(this.solicitud.getSolicitudById(id));
    }

    @PutMapping("/estado/{solicitudId}")
    ResponseEntity<String> updateSolicitud(@PathVariable Integer solicitudId, @RequestBody SolicitudRenting nuevoRenting){
        try{
            this.solicitud.modificaSolicitud(solicitudId,nuevoRenting);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("Solicitud con id de solicitud: "+ solicitudId+ ", actualizada"));
    }
}
