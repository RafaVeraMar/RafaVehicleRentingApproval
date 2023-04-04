package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import java.util.Map;

@Tag(name="Listar tipos de solicitudes",description = "Endpoint que devuelve una lista de los tipos de solicitudes existentes en la base de datos.")
@RestController
public class ResolucionSolicitudesController {

    private ResolucionSolicitudesService resolucionSolicitudesService;

    public ResolucionSolicitudesController(ResolucionSolicitudesService resolucionSolicitudesService) {
        this.resolucionSolicitudesService = resolucionSolicitudesService;
    }

    @GetMapping("/listarTiposResolucion")
    @ApiResponses( value = { @ApiResponse( responseCode = "200", description = "Existen datos en la base de datos y se devuelven.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen tipos de solicitudes en la base de datos.", content = { @Content( mediaType = "application/json")})
    })
    ResponseEntity listarTiposResolucion() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            return ResponseEntity.ok(this.resolucionSolicitudesService.getTipoResolucionesSolicitudes());
        }catch (ResolucionSolicitudesNotFoundException e){
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("descripcion", "No existen elementos en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.NO_CONTENT);
        }

        catch (Exception e) {
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("descripcion", "Error interno del servidor");
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
