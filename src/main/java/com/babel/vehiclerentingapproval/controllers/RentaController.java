package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RentaFoundException;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.services.RentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase define la documentación Swagger con el métodos para hacer el CRUD (Post) de las Renta
 * y sus rutas para poder acceder desde PostMan.
 *
 * @author: andres.guijarro@babelgroup.com
 */

@Tag(name = "Añadir renta", description = "Endpoint que dado un ID de persona y una renta (ambos en JSON) añade la renta a esa persona a la base de datos.")
@RestController
public class RentaController {

    /**
     * Contiene un mapper que realiza las acciones relacionadas con la renta
     */
    RentaService rentaService;
    private static final String DESCRIPCION = "Descripcion: ";
    private static final String STATUS = "status: ";

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    /**
     * Añade una nueva renta y devuelve un objeto ResponseEntity con la información
     * de la renta.
     * <p>
     * El método maneja las siguientes excepciones:
     * - PersonaNotFoundException si la persona asociada a la solicitud no existe.
     * - ProfesionNotFoundException lanza una excepcion cuando la renta ya existe en la base de datos.
     * - RentaFoundException lanza una excepcion cuando la renta ya existe en la base de datos.
     * <p>
     *
     * @param renta la renta que se va a añadir
     * @return un objeto ResponseEntity que contiene la información de la renta creada,
     * incluido su ID, y el código de estado HTTP
     */
    @PostMapping("/renta")
    @Operation(summary = "Añadir una renta", description = "Añade una renta a una persona")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "La renta ha añadido con éxito a la base de datos.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "La profesión no existe.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "La persona no existe.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406", description = "Se ha intendado introducir un ID de renta que ya existe.", content = {@Content(mediaType = "application/json")})
    })
    @Parameter(name = "personaId", description = "ID de una persona existente en la base de datos.", required = true)
    @Parameter(name = "renta", description = "Objeto Renta a insertar", required = true)
    public ResponseEntity<Object> addRenta(@RequestBody Renta renta) throws ProfesionNotFoundException, PersonaNotFoundException, RentaFoundException {
        Renta rentaActualizada;
        Map<String, Object> map = new HashMap<>();
        rentaActualizada = this.rentaService.addRenta(renta);
        map.put(STATUS, HttpStatus.OK);
        map.put(DESCRIPCION, "Renta añadida.");
        map.put("id", rentaActualizada.getRentaId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
