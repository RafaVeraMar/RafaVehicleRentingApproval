package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentaController {

    RentaService rentaService;

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @PostMapping("/renta")
    ResponseEntity addRenta(@RequestBody Renta renta){
        try {
            this.rentaService.addRenta(renta);
        } catch (ProfesionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Profesion no encontrada en la base de datos");
        } catch (PersonaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Persona no encontrada en la base de datos");
        }
        return ResponseEntity.ok(String.format("Renta añadida. Id: %d", renta.getRentaId()));
    }
}
