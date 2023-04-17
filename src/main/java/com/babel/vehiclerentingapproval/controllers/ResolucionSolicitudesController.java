package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase define el endpoint de listado de tipos de resoluciones posibles para una solicitud.
 *
 * @author andres.guijarro@babelgroup.com
 */
@Tag(name = "Listar tipos de solicitudes", description = "Endpoint que devuelve una lista de los tipos de solicitudes existentes en la base de datos.")
@RestController
public class ResolucionSolicitudesController {

    private ResolucionSolicitudesService resolucionSolicitudesService;

    public ResolucionSolicitudesController (ResolucionSolicitudesService resolucionSolicitudesService) {
        this.resolucionSolicitudesService = resolucionSolicitudesService;
    }

    /**
     * Devuelve un array de objetos en JSON con los códigos de resolución existentes en la base de datos.
     * <p>
     * El método maneja las siguientes excepciones:
     * - ResolucionSolicitudesNotFoundException si no se encuentra ningun codigo de resolucion de solicitudes.
     * <p>
     * En caso de cualquier otra excepción, se devuelve un error interno del servidor.
     *
     * @return un objeto ResponseEntity que contiene la información de la solicitud creada, que contiene los codigos de
     * resolución que existen
     */
    @GetMapping("/listarTiposResolucion")
    @Operation(summary = "Listar los tipos de resolucion de solicitudes", description = "Lista los tipos de resolucion de solicitudes existentes en la base de datos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Existen datos en la base de datos y se devuelven.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen tipos de solicitudes en la base de datos.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<Object> listarTiposResolucion ( ) {
        Map<String, Object> map = new HashMap<>();
        try {
            return ResponseEntity.ok(this.resolucionSolicitudesService.getTipoResolucionesSolicitudes());
        } catch (ResolucionSolicitudesNotFoundException e) {
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("descripcion", "No existen elementos en la base de datos");
            return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("descripcion", "Error interno del servidor");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
