package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.InputIsNull;
import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitud")
public class SolicitudRentingController {


    private final SolicitudRentingService solicitud;

    public SolicitudRentingController (SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }

    @PostMapping("")
    ResponseEntity addSolicitudRenting(@RequestBody SolicitudRenting solicitudRenting){
        try {
            solicitud.addSolicitudRenting(solicitudRenting);
        } catch (PersonaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La persona no existe en la base de datos.");
        } catch (WrongLenghtFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comprueba que no sobrepase la longitud de los datos de entrada.");
        } catch (InputIsNull e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comprueba los datos de entrada.");
        }
        return ResponseEntity.ok(String.format("Solicitud añadida. id: %d", solicitudRenting.getSolicitudId()));
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
    ResponseEntity muestraSolicitudPorId(@PathVariable int id){
       try{
           this.solicitud.getSolicitudById(id);
       }catch (SolicitudRentingNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de solicitud no es válido");
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
       return ResponseEntity.ok(this.solicitud.getSolicitudById(id));
    }

    @PutMapping("/estado/{solicitudId}")
    ResponseEntity<String> updateSolicitud(@PathVariable Integer solicitudId, @RequestBody SolicitudRenting nuevoRenting){
        try{
            this.solicitud.modificaSolicitud(solicitudId,nuevoRenting);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("Solicitud con id de solicitud: "+ solicitudId+ ", actualizada"));
    }
}
