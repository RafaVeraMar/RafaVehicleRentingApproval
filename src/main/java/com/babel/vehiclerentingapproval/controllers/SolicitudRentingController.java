package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private SolicitudRentingService solicitud;

    public SolicitudRentingController(SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }


    @GetMapping("/estado/{id}")
    @Operation(summary = "Ver estado de solicitud por ID", description = "Devuelve el estado de una solicitud a partir de su ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Estado de la solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error de formato en el ID", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id",description = "ID de la solicitud a consultar",required = true)
    ResponseEntity<Object> verEstadoSolicitud(@PathVariable String id) throws EstadoSolicitudNotFoundException {
        Map<String,Object> respuesta = new HashMap<String,Object>();
        try{
            int idSolicitud = Integer.parseInt(id);
            String estado = this.solicitud.verEstadoSolicitud(idSolicitud);

            respuesta.put("Status",HttpStatus.OK);
            respuesta.put("Id",id);
            respuesta.put("Descripcion",estado);
            return new ResponseEntity<Object>(respuesta,HttpStatus.OK);
        }
        catch (EstadoSolicitudNotFoundException e){
            respuesta.put("Status",HttpStatus.NOT_FOUND);
            respuesta.put("Id",id);
            respuesta.put("Descripcion","Error: No existe ninguna codigo de resolución válido asociado a esa solicitud ");
            return new ResponseEntity<Object>(respuesta,HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            respuesta.put("Status",HttpStatus.BAD_REQUEST);
            respuesta.put("Id",id);
            respuesta.put("Descripcion","Error: No ha introducido una id valida ");
            return new ResponseEntity<Object>(respuesta,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("{id}")
    @Operation(summary = "Mostrar solicitud por ID", description = "Devuelve una solicitud de renting, si existe una solicitud por su ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Solicitud por ID", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Petición de solicitud mal formada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id",description = "ID para comprobar si existe la solicitud",required = true)
    ResponseEntity muestraSolicitudPorId(@PathVariable int id){
       try{
           this.solicitud.getSolicitudById(id);
       }catch (SolicitudRentingNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de solicitud no es válido");
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       }
       return ResponseEntity.ok(this.solicitud.getSolicitudById(id));
    }

    @PutMapping("/estado/{solicitudId}")
    ResponseEntity<String> updateEstadoSolicitud(@PathVariable Integer solicitudId, @RequestBody TipoResultadoSolicitud nuevoEstado){
        try{
            this.solicitud.modificaEstadoSolicitud(solicitudId,nuevoEstado);
        }catch (SolicitudRentingNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de solicitud no es válido");
        }catch (EstadoSolicitudNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estado "+nuevoEstado.getCodResultado()+" de solicitud no es válido");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("Solicitud con id de solicitud: "+ solicitudId+ ", actualizada"));
    }
}
