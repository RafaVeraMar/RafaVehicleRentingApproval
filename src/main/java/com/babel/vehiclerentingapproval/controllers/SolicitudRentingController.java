package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
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
    ResponseEntity<String> updateEstadoSolicitud(@PathVariable Integer solicitudId, @RequestBody TipoResultadoSolicitud nuevoEstado){
        try{
            this.solicitud.modificaEstadoSolicitud(solicitudId,nuevoEstado);
        }catch (SolicitudRentingNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de solicitud no es válido");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("Solicitud con id de solicitud: "+ solicitudId+ ", actualizada"));
    }
}
