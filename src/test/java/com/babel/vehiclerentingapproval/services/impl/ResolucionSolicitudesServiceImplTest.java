package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ResolucionSolicitudesMapper;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class ResolucionSolicitudesServiceImplTest {
    ResolucionSolicitudesService solicitudesService;
    ResolucionSolicitudesMapper solicitudesMapper;

    @BeforeEach
    void setupAll ( ) {

        solicitudesMapper = Mockito.mock(ResolucionSolicitudesMapper.class);
        when(solicitudesMapper.getTipoResolucionesSolicitudes()).thenReturn(crearListaVacia());

        solicitudesService = new ResolucionSolicitudesServiceImpl(solicitudesMapper);
    }

    @Test
    void listar_should_throwResolucionSolicitudesNotFoundException_when_noHayDatosEnBaseDeDatos ( ) {
        Assertions.assertThrows(ResolucionSolicitudesNotFoundException.class, ( ) -> {
            solicitudesService.getTipoResolucionesSolicitudes();
        });
    }

    private List<ResolucionSolicitud> crearListaConElementos ( ) {
        List<ResolucionSolicitud> lista = new ArrayList<ResolucionSolicitud>();
        lista.add(new ResolucionSolicitud("AA", "Aprobada"));
        return lista;
    }

    private List<ResolucionSolicitud> crearListaVacia ( ) {
        List<ResolucionSolicitud> lista = new ArrayList<ResolucionSolicitud>();
        return lista;
    }
}
