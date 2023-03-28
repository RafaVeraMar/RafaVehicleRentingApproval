package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitud")
public class SolicitudRentingController {


    private final SolicitudRentingService solicitudRentingService;

    public SolicitudRentingController (SolicitudRentingService solicitudRentingService) {
        this.solicitudRentingService = solicitudRentingService;
    }

    @PostMapping("")
    ResponseEntity addSolicitudRenting(@RequestBody SolicitudRenting solicitudRenting){
        solicitudRentingService.addSolicitudRenting(solicitudRenting);
        return ResponseEntity.ok(String.format("Solicitud añadida. id: %d", solicitudRenting.getSolicitudId()));
    }

}
