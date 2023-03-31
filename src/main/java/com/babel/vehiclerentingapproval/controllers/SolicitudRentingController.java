package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    private SolicitudRentingService solicitud;
    private SolicitudRentingMapper solicitudRentingMapper;


    public SolicitudRentingController(SolicitudRentingService solicitudService, SolicitudRentingMapper solicitudRentingMapper) {
        this.solicitud = solicitudService;
        this.solicitudRentingMapper = solicitudRentingMapper;
    }

    @GetMapping("/estado/{id}")
    ResponseEntity<String> verEstadoSolicitud(@PathVariable String id) throws EstadoSolicitudNotFoundException {

        try {
            int idSolicitud = Integer.parseInt(id);
            String estado = this.solicitud.verEstadoSolicitud(idSolicitud);
            return ResponseEntity.ok(estado);
        } catch (EstadoSolicitudNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No existe ninguna codigo de resolución válido asociado a esa solicitud ");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Debe de pasar un número como id ");
        }
    }

    @GetMapping("{id}")
    ResponseEntity<SolicitudRenting> muestraSolicitudPorId(@PathVariable int id) {
        return ResponseEntity.ok(this.solicitud.getSolicitudById(id));
    }

    @Operation(summary = "Ver estado de solicitud por ID", description = "Devuelve el estado de una solicitud a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cancelada la solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error con el id al cancelar solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "ID de la solicitud a cancelar", required = true)
    @PatchMapping("/{id}")
    ResponseEntity cancelarSolicitud(@PathVariable int id) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            this.solicitud.cancelarSolicitud(id);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "Solicitud cancelada");
            return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
        } catch (SolicitudRentingNotFoundException e) {
            respuesta.put("Status", HttpStatus.NOT_FOUND);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "El id de solicitud no es válido");
            return new ResponseEntity<Object>(respuesta, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            respuesta.put("Status", HttpStatus.BAD_REQUEST);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "Error: No ha introducido una id valida ");
            return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }
}


