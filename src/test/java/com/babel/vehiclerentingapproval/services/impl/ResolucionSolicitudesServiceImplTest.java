package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ResolucionSolicitudesMapper;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResolucionSolicitudesServiceImplTest {
    ResolucionSolicitudesService solicitudesService;
    ResolucionSolicitudesService solicitudesServiceVacio;
    ResolucionSolicitudesMapper solicitudesMapper;

    @BeforeEach
    void setupAll() throws ResolucionSolicitudesNotFoundException {
        solicitudesService = Mockito.mock(ResolucionSolicitudesServiceImpl.class);
        when(solicitudesService.listar()).thenReturn(crearListaConElementos());

        solicitudesServiceVacio = Mockito.mock(ResolucionSolicitudesServiceImpl.class);
        when(solicitudesServiceVacio.listar()).thenReturn(crearListaSinElementos());

        solicitudesMapper = Mockito.mock(ResolucionSolicitudesMapper.class);
        when(solicitudesMapper.listar()).thenReturn(crearListaConElementos());
    }

    @Test
    public void listar_should_throwResolucionSolicitudesNotFoundException_when_noHayDatosEnBaseDeDatos(){
        Assertions.assertThrows(ResolucionSolicitudesNotFoundException.class, () -> {
            solicitudesServiceVacio.listar();
        });
    }

    private List<ResolucionSolicitud> crearListaConElementos(){
        List<ResolucionSolicitud> lista = null;
        lista.add(new ResolucionSolicitud("AA","Aprobada"));
        return lista;
    }

    private List<ResolucionSolicitud> crearListaSinElementos(){
        List<ResolucionSolicitud> lista = null;
        lista.add(new ResolucionSolicitud("AA","Aprobada"));
        lista.remove(0); //Lista vacia
        return lista;
    }
}
