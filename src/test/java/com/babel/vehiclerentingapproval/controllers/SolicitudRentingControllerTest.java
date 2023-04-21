package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.PersonaServiceImpl;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SolicitudRentingControllerTest {
    SolicitudRentingController solicitudRentingController;
    SolicitudRentingService solicitudRentingService;
    SolicitudRentingMapper solicitudRentingMapper;
    PersonaMapper personaMapper;

    @BeforeEach
    void setupAll(){
        solicitudRentingMapper= Mockito.mock(SolicitudRentingMapper.class);
        when(solicitudRentingMapper.existeSolicitud(1)).thenReturn(1);
        personaMapper= Mockito.mock(PersonaMapper.class);
        when(personaMapper.existePersona(1)).thenReturn(1);

        TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        PersonaService personaService = Mockito.mock(PersonaService.class);
        CodigoResolucionValidator codigoResolucionValidator = Mockito.mock(CodigoResolucionValidator.class);
        PersonaMapper personaMapper = Mockito.mock(PersonaMapper.class);

        solicitudRentingService = new SolicitudRentingServiceImpl(solicitudRentingMapper,tipoResultadoSolicitudMapper,personaService,codigoResolucionValidator,personaMapper);
    }
    private SolicitudRenting creaSolicitudFicticia() throws ParseException {
        SolicitudRenting solicitudFicticia = new SolicitudRenting();
        Persona personaFicticia = new Persona();
        Direccion direccionFicticia = new Direccion();
        TipoResultadoSolicitud tipoResultadoSolicitudFicticia = new TipoResultadoSolicitud();
        tipoResultadoSolicitudFicticia.setCodResultado("1");
        tipoResultadoSolicitudFicticia.setDescripcion("sadasdadasd");
        direccionFicticia.setDireccionId(1);
        direccionFicticia.setTipoViaId(new TipoVia(1, "Alameda"));
        direccionFicticia.setNombreCalle("Alosno");
        direccionFicticia.setNumero("5");
        direccionFicticia.setCodPostal("21006");
        direccionFicticia.setMunicipio("Huelva");
        direccionFicticia.setProvinciaCod(new Provincia("02", "Albacete"));
        personaFicticia.setPersonaId(1);
        personaFicticia.setNombre("Migue");
        personaFicticia.setApellido1("Estevez");
        personaFicticia.setDireccionDomicilio(direccionFicticia);
        personaFicticia.setDireccionNotificacion(direccionFicticia);
        personaFicticia.setDireccionDomicilioSameAsNotificacion(false);
        personaFicticia.setNif("4444444E");
        personaFicticia.setNacionalidad(new Pais("AD", 20, "AND", "Andorra", 2));
        personaFicticia.setEmail("puyoleldelbarsa@gmail.com");
        personaFicticia.setFechaScoring(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2023"));
        solicitudFicticia.setPersona(personaFicticia);
        solicitudFicticia.setSolicitudId(1);
        solicitudFicticia.setFechaSolicitud(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2023"));
        solicitudFicticia.setNumVehiculos(BigInteger.valueOf(3));
        solicitudFicticia.setInversion(40f);
        solicitudFicticia.setCuota(5f);
        solicitudFicticia.setPlazo(BigInteger.valueOf(4));
        solicitudFicticia.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2024"));
        solicitudFicticia.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2024"));
        solicitudFicticia.setTipoResultadoSolicitud(tipoResultadoSolicitudFicticia);
        return solicitudFicticia;
    }

    @Test
    void testAddSolicitudRentingSuccess() throws Exception {
        SolicitudRentingService solicitudRentingService = Mockito.mock(SolicitudRentingService.class);
        SolicitudRentingController solicitudRentingController = new SolicitudRentingController(solicitudRentingService);
        SolicitudRenting solicitudRenting = creaSolicitudFicticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.when(solicitudRentingService.addSolicitudRenting(solicitudRenting)).thenReturn(solicitudRenting);

        ResponseEntity response = solicitudRentingController.addSolicitudRenting(solicitudRenting);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testVerEstadoSolicitudSuccess() throws Exception {
        SolicitudRentingService solicitudRentingService = Mockito.mock(SolicitudRentingService.class);
        SolicitudRentingController solicitudRentingController = new SolicitudRentingController(solicitudRentingService);
        SolicitudRenting solicitudRenting = creaSolicitudFicticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.when(solicitudRentingService.verEstadoSolicitud(solicitudRenting.getSolicitudId())).thenReturn("1");

        ResponseEntity response = solicitudRentingController.verEstadoSolicitud(String.valueOf(solicitudRenting.getSolicitudId()));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCancelarSolicitudSuccess() throws Exception {
        SolicitudRentingService solicitudRentingService = Mockito.mock(SolicitudRentingService.class);
        SolicitudRentingController solicitudRentingController = new SolicitudRentingController(solicitudRentingService);
        SolicitudRenting solicitudRenting = creaSolicitudFicticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.doNothing().when(solicitudRentingService).cancelarSolicitud(solicitudRenting.getSolicitudId());

        ResponseEntity response = solicitudRentingController.cancelarSolicitud(solicitudRenting.getSolicitudId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateEstadoSolicitudSuccess() throws Exception {
        SolicitudRentingService solicitudRentingService = Mockito.mock(SolicitudRentingService.class);
        SolicitudRentingController solicitudRentingController = new SolicitudRentingController(solicitudRentingService);
        SolicitudRenting solicitudRenting = creaSolicitudFicticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.doNothing().when(solicitudRentingService).modificaEstadoSolicitud(solicitudRenting.getSolicitudId(),solicitudRenting.getTipoResultadoSolicitud());


        ResponseEntity response = solicitudRentingController.updateEstadoSolicitud(solicitudRenting.getSolicitudId(),solicitudRenting.getTipoResultadoSolicitud());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void testMuestraSolicitudPorIdSuccess() throws Exception {
        SolicitudRentingService solicitudRentingService = Mockito.mock(SolicitudRentingService.class);
        SolicitudRentingController solicitudRentingController = new SolicitudRentingController(solicitudRentingService);
        SolicitudRenting solicitudRenting = creaSolicitudFicticia();

        // Configurar el comportamiento de personaService.addPersona()
        //Mockito.doNothing().when(solicitudRentingService).getSolicitudById(solicitudRenting.getSolicitudId());
        Mockito.when(solicitudRentingService.getSolicitudById(solicitudRenting.getSolicitudId())).thenReturn(solicitudRenting);

        ResponseEntity response = solicitudRentingController.muestraSolicitudPorId(solicitudRenting.getSolicitudId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
