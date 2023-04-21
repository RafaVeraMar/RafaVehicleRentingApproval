package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.PersonaServiceImpl;
import com.babel.vehiclerentingapproval.services.impl.ResolucionSolicitudesServiceImpl;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

class ResolucionSolicitudesControllerTest {
    ResolucionSolicitudesService resolucionSolicitudesService;
    ResolucionSolicitudesMapper resolucionSolicitudesMapper;

    @BeforeEach
    void setupAll() {
        resolucionSolicitudesMapper = Mockito.mock(ResolucionSolicitudesMapper.class);
        resolucionSolicitudesService = new ResolucionSolicitudesServiceImpl(resolucionSolicitudesMapper);

    }

    List<ResolucionSolicitud> createListaMock() {
        List<ResolucionSolicitud> lista = new ArrayList<ResolucionSolicitud>();
        lista.add(new ResolucionSolicitud("AA", "Aprobada"));
        System.out.println(lista);
        return lista;
    }
    private List<ResolucionSolicitud> crearListaVacia ( ) {
        List<ResolucionSolicitud> lista = new ArrayList<ResolucionSolicitud>();
        return lista;
    }

    @Test
    void testListarTipoSuccess() throws Exception {
        resolucionSolicitudesService = Mockito.mock(ResolucionSolicitudesService.class);
        ResolucionSolicitudesController resolucionSolicitudesController = new ResolucionSolicitudesController(resolucionSolicitudesService);

        List<ResolucionSolicitud> listaTest = createListaMock();

        Mockito.when(resolucionSolicitudesService.getTipoResolucionesSolicitudes()).thenReturn(listaTest);


        ResponseEntity response = resolucionSolicitudesController.listarTiposResolucion();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



}
