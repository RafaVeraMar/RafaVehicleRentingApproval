package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;

    @BeforeEach
    void setUpAll(){
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper,tipoResultadoSolicitudMapper);

    }

    @Nested
    class TestsVerEstadoSolicitud{
        @Test
        public void verEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_or_idNotExists(){
            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(anyInt())).thenReturn(0);
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class,() ->{
                String estado = solicitudService.verEstadoSolicitud(anyInt());
            });
        }
        @Test
        public void verEstadoSolicitud_shouldNotThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_and_idNotExists(){
            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(()->{
                String estado = solicitudService.verEstadoSolicitud(anyInt());
            });
        }
    }

    @Nested
    class TestsVerSolicitudRenting{
        @Test
        public void verSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists(){
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);

            Assertions.assertThrows(SolicitudRentingNotFoundException.class, ()->{
                solicitudService.getSolicitudById(0);
            });

        }
        @Test
        public void verSolicitudRenting_shouldNotThrow_SolicitudRentingNotFoundException_when_solicitudIdExists(){
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(()->{
                solicitudService.getSolicitudById(0);
            });

        }
    }

}
