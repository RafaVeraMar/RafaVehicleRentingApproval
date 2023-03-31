package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import java.util.Map;


@RestController
public class ResolucionSolicitudesController {

    private ResolucionSolicitudesService resolucionSolicitudesService;

    public ResolucionSolicitudesController(ResolucionSolicitudesService resolucionSolicitudesService) {
        this.resolucionSolicitudesService = resolucionSolicitudesService;
    }

    @GetMapping("/listarTiposResolucion")
    ResponseEntity listarTiposResolucion() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            return ResponseEntity.ok(this.resolucionSolicitudesService.listar());
        }catch (ResolucionSolicitudesNotFoundException e){
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("descripcion", "No existen elementos en la base de datos");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("descripcion", "Error interno del servidor");
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
