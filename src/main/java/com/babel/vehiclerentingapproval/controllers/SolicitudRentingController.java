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

/**
 * Esta clase define la documentación Swagger con los métodos para hacer el CRUD (Crear, Ver, Modificar y Cancelar) de las Solicitudes de Renting
 * y sus rutas para poder acceder desde PostMan.
 *
 * @author @author miguel.sdela@babelgroup.com, javier.serrano@babelgroup.com, ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com, javier.roldan@babelgroup.com
 */
@RestController
@Tag(name = "Operaciones con Solicitud Renting", description = "Endpoint permite operar con las solicitudes de renting.")
@RequestMapping("/solicitud")
public class SolicitudRentingController {


    private final SolicitudRentingService solicitud;


    public SolicitudRentingController(SolicitudRentingService solicitud) {
        this.solicitud = solicitud;
    }


    /**
     * Añade una nueva solicitud de renting y devuelve un objeto ResponseEntity con la información
     * de la solicitud creada, incluido su ID.
     * <p>
     * El método maneja las siguientes excepciones:
     * - PersonaNotFoundException si la persona asociada a la solicitud no existe.
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.
     * - InputIsNullOrIsEmpty si alguno de los datos es nulo o no se ha rellenado.
     * - DateIsBeforeException si la fecha de inicio de vigor es anterior a la fecha de resolución.
     * - InputIsNegativeOrZeroException si los datos de entrada tienen que ser mayores que 0.
     * <p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param solicitudRenting la solicitud de renting que se va a añadir
     * @return un objeto ResponseEntity que contiene la información de la solicitud creada,
     * incluido su ID, y el código de estado HTTP
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
    ResponseEntity addSolicitudRenting(@RequestBody SolicitudRenting solicitudRenting) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            solicitud.addSolicitudRenting(solicitudRenting);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Id", solicitudRenting.getSolicitudId());
            respuesta.put("Descripcion:", "Solicitud creada correctamente");
        } catch (RequestApiValidationException e) {
            respuesta.put("Status", e.getStatusCode());
            respuesta.put("Descripcion:", e.getExternalMessage());
        } catch (Exception e) {
            respuesta.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("Descripcion:", "Error interno, intentelo de nuevo mas tarde.");
        }
        return new ResponseEntity<Object>(respuesta, (HttpStatus)respuesta.get("Status"));
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
    @Parameter(name = "id", description = "ID de la solicitud a consultar", required = true)
    ResponseEntity<Object> verEstadoSolicitud(@PathVariable String id) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
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
    ResponseEntity muestraSolicitudPorId(@PathVariable int id) throws RequestApiValidationException {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            this.solicitud.getSolicitudById(id);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Solicitud: ", solicitud.getSolicitudById(id));
        } catch (RequestApiValidationException e) {
            respuesta.put("Status", e.getStatusCode());
            respuesta.put("Id", id);
            respuesta.put("Descripcion: ", e.getExternalMessage());
        } catch (Exception e) {
            respuesta.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("Descripcion: ", "Error interno.");
        }
        return new ResponseEntity<Object>(respuesta, (HttpStatus) respuesta.get("Status"));
    }

    /**
     *  Metodo que se encarga de cancelar la solicitud de renting asociada al id de esta, que se pasa como parametro en la funcion.
     *  Ademas se implementa la documentacion con swagger
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
    public ResponseEntity cancelarSolicitud(@PathVariable int id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            this.solicitud.cancelarSolicitud(id);
            respuesta.put("Status", HttpStatus.OK);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "Solicitud cancelada");
        } catch (RequestApiValidationException e) {
            respuesta.put("Status", e.getStatusCode());
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "El id de solicitud no es válido");
        } catch (Exception e) {
            respuesta.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("Id", id);
            respuesta.put("Descripcion", "Error: No ha introducido una id valida ");
        }
        return new ResponseEntity<Object>(respuesta, (HttpStatus) respuesta.get("Status"));
    }

    /**
     * Endpoint para actualizar el estado de una solicitud de renting
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
    ResponseEntity<Object> updateEstadoSolicitud(@PathVariable Integer solicitudId, @RequestBody TipoResultadoSolicitud nuevoEstado) {
        Map<String, Object> respuestaJson = new HashMap<String, Object>();
        try {
            this.solicitud.modificaEstadoSolicitud(solicitudId, nuevoEstado);
            respuestaJson.put("Status", HttpStatus.OK);
            respuestaJson.put("Id", solicitudId);
            respuestaJson.put("Descripcion", "La solicitud ha sido modificada y se ha notificado al usuario");
            return new ResponseEntity<Object>(respuestaJson, HttpStatus.OK);
        } catch (SolicitudRentingNotFoundException e) {
            respuestaJson.put("Status", 407);
            respuestaJson.put("Id", solicitudId);
            respuestaJson.put("Descripcion", "Error: No se encuentra la solicitud buscada, intentelo mas tarde");
            return new ResponseEntity<Object>(respuestaJson, HttpStatus.NOT_FOUND);
        } catch (EstadoSolicitudNotFoundException e) {
            respuestaJson.put("Status", 408);
            respuestaJson.put("Descripcion", "Error: Estado de solicitud: " + nuevoEstado.getCodResultado() + ", no valido");
            respuestaJson.put("Id", solicitudId);
            respuestaJson.put("CodigoResolucion", nuevoEstado.getCodResultado());
            respuestaJson.put("CodigoDescripcion", nuevoEstado.getDescripcion());
            return new ResponseEntity<Object>(respuestaJson, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            respuestaJson.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaJson.put("Id", solicitudId);
            respuestaJson.put("Descripcion", "Error: Fallo interno en el servidor, disculpad las molestias");
            return new ResponseEntity<Object>(respuestaJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


