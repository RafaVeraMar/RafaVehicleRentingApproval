package com.babel.vehiclerentingapproval.controllers;


import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collections;

import java.util.Map;

/**
 * Esta clase define el endpoint para operar con las solicitudes de renting
 *
 * @author @author miguel.sdela@babelgroup.com, javier.serrano@babelgroup.com, ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com, javier.roldan@babelgroup.com
 */
@ControllerAdvice
@RestController
@Tag(name = "Operaciones con Solicitud Renting", description = "Endpoint permite operar con las solicitudes de renting.")
@RequestMapping("/solicitud")
@Log4j2
public class SolicitudRentingController {


    private final SolicitudRentingService solicitud;


    public SolicitudRentingController (SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Añade una nueva solicitud de renting y devuelve la respuesta con el ID de la solicitud creada.
     *
     * @param solicitudRenting objeto SolicitudRenting que contiene la información de la solicitud
     * @return ResponseEntity<Object> que contiene la respuesta con el ID de la solicitud creada y el HttpStatus
     */
    @PostMapping("")
    @Operation(summary = "Introducir una nueva solicitud de renting", description = "Devuelve el id de la solicitud si se ha introducido correctamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "La persona asociada a la solicitud no existe.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Los datos de entrada sobrepasan la longitud maxima.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Alguno de los datos son nulos o no se han rellenado.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "La fecha de inicio de vigor, no puede ser anterior a la fecha de resolucion", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Los datos de entrada tienen que ser mayor que 0", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Object> addSolicitudRenting (@RequestBody SolicitudRenting solicitudRenting) {
        log.info("Entrando en addSolicitudRenting en SolicitudRentingController");
        solicitud.addSolicitudRenting(solicitudRenting);

        log.info("Saliendo de addSolicitudRenting en SolicitudRentingController");
        return new ResponseEntity<>(Map.of("id",solicitudRenting.getSolicitudId()), HttpStatus.CREATED);
    }

    /**
     * Endpoint para ver el estado de una solicitud de renting
     *
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
    @Parameter(name = "id", description = "ID de la solicitud a consultar", required = true)
    public ResponseEntity<Object> verEstadoSolicitud (@PathVariable String id) {
        log.info("Entrando en verEstadoSolicitud en SolicitudRentingController");
        String estado = this.solicitud.verEstadoSolicitud(id);
        log.info("Saliendo de verEstadoSolicitud en SolicitudRentingController");
        return new ResponseEntity<>(Collections.singletonMap("Estado", estado),HttpStatus.OK);
    }

    /**
     * Método que muestra las solicitudes por su ID
     *
     * @param id El parámetro ID es el ID de la solicitud que se quiere ver
     * @return Devuelve un objeto Solicitud con todos sus campos
     */
    @GetMapping("{id}")
    @Operation(summary = "Mostrar solicitud por ID", description = "Devuelve una solicitud de renting, si existe una solicitud por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud por ID", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "ID para comprobar si existe la solicitud", required = true)
    public ResponseEntity<Object> muestraSolicitudPorId (@PathVariable("id") int id) {
        log.info("Entrando en muestraSolicitudPorID en SolicitudRentingController");
        return solicitud != null ? ResponseEntity.ok(Collections.singletonMap("Solicitud", this.solicitud.getSolicitudById(id))) : ResponseEntity.notFound().build();
    }

    /**
     * Metodo que se encarga de cancelar la solicitud de renting asociada al id de esta, que se pasa como parametro en la funcion.
     * Ademas se implementa la documentacion con swagger
     *
     * @param id es la id de la solicitud de renting que queremos cancelar
     * @return se devuelve un json en el que se informa de que la solicitud ha sido cancelada
     */
    @Operation(summary = "Cancela una solicitud", description = "Cancela una solicitud dada su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cancelada la solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error con el id al cancelar solicitud", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "ID de solicitud no encontrado", content = @Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "ID de la solicitud a cancelar", required = true)
    @PutMapping("/{id}")
    public ResponseEntity<Object> cancelarSolicitud (@PathVariable int id) {
        log.info("Entrando en cancelarSolicitud en SolicitudRentingController");
        this.solicitud.cancelarSolicitud(id);
        log.info("Saliendo de cancelarSolicitud en SolicitudRentingController");
        return ResponseEntity.ok(Collections.singletonMap("descripcion", "Solicitud cancelada"));
    }

    /**
     * Endpoint para actualizar el estado de una solicitud de renting
     *
     * @param solicitudId ID de la solicitud de renting
     * @param nuevoEstado valor del nuevo estado de la solicitud de renting
     * @return Objeto ResponseEntity que devuelve un Json con el código de la operación REST, ID de solicitud y código de resolución
     */
    @PutMapping("/estado/{solicitudId}")
    @Operation(summary = "Modificar estado de solicitud por ID", description = "Modifica el estado de una solicitud a partir de su ID y notifica al usuario enviando un correo electronico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado solicitud correcto.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "407", description = "No se encuentra la solicitud buscada.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "408", description = "Estado de solicitud no valido.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<Object> updateEstadoSolicitud (@PathVariable Integer solicitudId, @RequestBody TipoResultadoSolicitud nuevoEstado) throws MessagingException {
        log.info("Entrando en updateEstadoSolicitud en SolicitudRentingController");
        solicitud.modificaEstadoSolicitud(solicitudId, nuevoEstado);
        log.info("Saliendo de updateEstadoSolicitud en SolicitudRentingController");
        return new ResponseEntity<>(Collections.singletonMap("Id", solicitudId), HttpStatus.OK);
    }
}


