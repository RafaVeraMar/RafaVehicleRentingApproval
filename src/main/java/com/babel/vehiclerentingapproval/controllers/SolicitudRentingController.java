package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/solicitud")
public class SolicitudRentingController {
    private SolicitudRentingService solicitud;

    public SolicitudRentingController(SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }


    @GetMapping("/estado/{id}")
    ResponseEntity<Object> verEstadoSolicitud(@PathVariable String id) throws EstadoSolicitudNotFoundException {
        Map<String,Object> respuesta = new HashMap<String,Object>();
        try{
            int idSolicitud = Integer.parseInt(id);
            String estado = this.solicitud.verEstadoSolicitud(idSolicitud);

            respuesta.put("Status",HttpStatus.OK);
            respuesta.put("Id",id);
            respuesta.put("Descripcion",estado);
            return new ResponseEntity<Object>(respuesta,HttpStatus.OK);
        }
        catch (EstadoSolicitudNotFoundException e){
            respuesta.put("Status",HttpStatus.NOT_FOUND);
            respuesta.put("Id",id);
            respuesta.put("Descripcion","Error: No existe ninguna codigo de resolución válido asociado a esa solicitud ");
            return new ResponseEntity<Object>(respuesta,HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            respuesta.put("Status",HttpStatus.BAD_REQUEST);
            respuesta.put("Id",id);
            respuesta.put("Descripcion","Error: No ha introducido una id valida ");
            return new ResponseEntity<Object>(respuesta,HttpStatus.NOT_FOUND);
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
