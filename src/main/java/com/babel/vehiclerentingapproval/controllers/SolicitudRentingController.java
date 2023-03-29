package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.InputIsNull;
import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.HttpStatus;
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
        try {
            solicitudRentingService.addSolicitudRenting(solicitudRenting);
        } catch (PersonaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La persona no existe en la base de datos.");
        } catch (WrongLenghtFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comprueba que no sobrepase la longitud de los datos de entrada.");
        } catch (InputIsNull e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comprueba los datos de entrada.");
        }
        return ResponseEntity.ok(String.format("Solicitud añadida. id: %d", solicitudRenting.getSolicitudId()));
    }

}
