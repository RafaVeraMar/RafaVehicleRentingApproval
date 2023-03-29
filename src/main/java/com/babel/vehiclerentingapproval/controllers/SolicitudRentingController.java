package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitud")
public class SolicitudRentingController {
    private SolicitudRentingService solicitud;

    public SolicitudRentingController(SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }

    @PostMapping("")
    ResponseEntity<String> crearSolicitud(@RequestBody SolicitudRenting solicitudACrear){
        this.solicitud.createRentingRequest(solicitudACrear);
        return ResponseEntity.ok("Solicitud creada");
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
    ResponseEntity<SolicitudRenting> muestraSolicitudPorId(@PathVariable int id){
       return ResponseEntity.ok(this.solicitud.getSolicitudById(id));

    }
}
