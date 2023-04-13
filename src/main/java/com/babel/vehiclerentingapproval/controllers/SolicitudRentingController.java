package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name="Operaciones con Solicitud Renting",description = "Endpoint permite operar con las solicitudes de renting.")
@RequestMapping("/solicitud")
public class SolicitudRentingController {


    private final SolicitudRentingService solicitud;
    private SolicitudRentingMapper solicitudRentingMapper;

    public SolicitudRentingController (SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }

    @PostMapping("")
    @Operation(summary = "Introducir una nueva solicitud de renting", description = "Devuelve el id de la solicitud si se ha introducido correctamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "La persona asociada a la solicitud no existe.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Los datos de entrada sobrepasan la longitud maxima.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Alguno de los datos son nulos o no se han rellenado.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "La fecha de inicio de vigor, no puede ser anterior a la fecha de resolucion", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Los datos de entrada tienen que ser mayor que 0", content = @Content(mediaType = "application/json")),
    })
    ResponseEntity addSolicitudRenting (@RequestBody SolicitudRenting solicitudRenting) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            solicitud.addSolicitudRenting(solicitudRenting);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "Solicitud creada correctamente");
            return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
        } catch (RequestApiValidationException e) {
            respuesta.put("Status", e.getStatusCode());
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", e.getExternalMessage());
            return new ResponseEntity<Object>(respuesta, e.getStatusCode());
        } catch (Exception e) {
            respuesta.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("Descripcion:", "Error interno, intentelo de nuevo mas tarde.");
            return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para ver el estado de una solicitud de renting
     * @param id ID de la solicitud de renting
     * @return Objeto ResponseEntity con el estado HTTP de la solicitud, el id proporcionado y el estado de la solicitud o la descripción del error
     */
    @GetMapping("/estado/{id}")
    @Operation(summary = "Ver estado de solicitud por ID", description = "Devuelve el estado de una solicitud a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error de formato en el ID", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor: el cod de resolución no es válido", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id",description = "ID de la solicitud a consultar",required = true)
    ResponseEntity<Object> verEstadoSolicitud(@PathVariable String id){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        try{
            int idSolicitud = Integer.parseInt(id);
            String estado = this.solicitud.verEstadoSolicitud(idSolicitud);

            respuesta.put("Status",HttpStatus.OK);
            respuesta.put("Id",id);
            respuesta.put("Descripcion",estado);
        }
        catch (NumberFormatException e){
            respuesta.put("Status",HttpStatus.BAD_REQUEST);
            respuesta.put("Id",id);
            respuesta.put("Descripcion","Error: el formato de ID es inválido");
        }
        catch (RequestApiValidationException e) {
            respuesta.put("Status", e.getStatusCode());
            respuesta.put("Id", id);
            respuesta.put("Descripcion:", e.getExternalMessage());
        }
        catch (Exception e){
            respuesta.put("Status",HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("Id",id);
            respuesta.put("Descripcion","Error: Ha ocurrido un error interno en el servidor ");
        }

        return new ResponseEntity<Object>(respuesta,(HttpStatus)respuesta.get("Status"));
    }

    @GetMapping("{id}")
    @Operation(summary = "Mostrar solicitud por ID", description = "Devuelve una solicitud de renting, si existe una solicitud por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud por ID", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Petición de solicitud mal formada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "ID para comprobar si existe la solicitud", required = true)
    ResponseEntity muestraSolicitudPorId (@PathVariable int id) {
        try {
            this.solicitud.getSolicitudById(id);
        } catch (SolicitudRentingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de solicitud no es válido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(this.solicitud.getSolicitudById(id));
    }


    @Operation(summary = "Cancela una solicitud", description = "Cancela una solicitud dada su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cancelada la solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error con el id al cancelar solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "ID de la solicitud a cancelar", required = true)
    @PutMapping("/{id}")
    ResponseEntity cancelarSolicitud (@PathVariable int id) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            this.solicitud.cancelarSolicitud(id);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "Solicitud cancelada");
            return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
        } catch (SolicitudRentingNotFoundException e) {
            respuesta.put("Status", HttpStatus.NOT_FOUND);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "El id de solicitud no es válido");
            return new ResponseEntity<Object>(respuesta, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            respuesta.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "Error: No ha introducido una id valida ");
            return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/estado/{solicitudId}")
    @Operation(summary = "Modificar estado de solicitud por ID", description = "Modifica el estado de una solicitud a partir de su ID y notifica al usuario enviando un correo electronico")
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Estado solicitud correcto.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "407", description = "No se encuentra la solicitud buscada.", content = { @Content( mediaType = "application/json")}),
            @ApiResponse(responseCode = "408", description = "Estado de solicitud no valido.", content = { @Content( mediaType = "application/json")})
    })
    ResponseEntity<Object> updateEstadoSolicitud(@PathVariable Integer solicitudId, @RequestBody TipoResultadoSolicitud nuevoEstado){
        Map<String,Object> respuestaJson = new HashMap<String,Object>();
        try{
            this.solicitud.modificaEstadoSolicitud(solicitudId,nuevoEstado);
            respuestaJson.put("Status",HttpStatus.OK);
            respuestaJson.put("Id",solicitudId);
            respuestaJson.put("Descripcion","La solicitud ha sido modificada y se ha notificado al usuario");
            return new ResponseEntity<Object>(respuestaJson,HttpStatus.OK);
        }catch (SolicitudRentingNotFoundException e){
            respuestaJson.put("Status",407);
            respuestaJson.put("Id",solicitudId);
            respuestaJson.put("Descripcion","Error: No se encuentra la solicitud buscada, intentelo mas tarde");
            return new ResponseEntity<Object>(respuestaJson,HttpStatus.NOT_FOUND);
        }catch (EstadoSolicitudNotFoundException e) {
            respuestaJson.put("Status",408);
            respuestaJson.put("Descripcion","Error: Estado de solicitud: "+nuevoEstado.getCodResultado() +", no valido");
            respuestaJson.put("Id",solicitudId);
            respuestaJson.put("CodigoResolucion",nuevoEstado.getCodResultado());
            respuestaJson.put("CodigoDescripcion",nuevoEstado.getDescripcion());
            return new ResponseEntity<Object>(respuestaJson,HttpStatus.NOT_FOUND);
        }catch(Exception e){
            respuestaJson.put("Status",HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaJson.put("Id",solicitudId);
            respuestaJson.put("Descripcion","Error: Fallo interno en el servidor, disculpad las molestias");
            return new ResponseEntity<Object>(respuestaJson,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


