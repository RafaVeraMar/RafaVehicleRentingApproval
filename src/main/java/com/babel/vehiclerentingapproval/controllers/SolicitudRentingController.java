package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/solicitud")
public class SolicitudRentingController {
    private SolicitudRentingService solicitud;
    private SolicitudRentingMapper solicitudRentingMapper;


    public SolicitudRentingController(SolicitudRentingService solicitudService, SolicitudRentingMapper solicitudRentingMapper) {
        this.solicitud = solicitudService;
        this.solicitudRentingMapper = solicitudRentingMapper;
    }

    @PostMapping("")
    ResponseEntity<String> crearSolicitud(@RequestBody SolicitudRenting solicitudACrear){
        this.solicitud.createRentingRequest(solicitudACrear);
        return ResponseEntity.ok("Solicitud creada");
    }
    @PatchMapping("/{id}")
    ResponseEntity<String> cancelarSolicitud(@PathVariable int id){
        this.solicitud.cancelarSolicitud(this.solicitud.getSolicitudById(id));
        return ResponseEntity.ok("Solicitud Renting actualizado");
    }
}
