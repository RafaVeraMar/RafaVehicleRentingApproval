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

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;

    @BeforeEach
    void setUpAll() {
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper, tipoResultadoSolicitudMapper);

    }

    @Test
    public void verEstadoSolicitud_should_throwEstadoSolicitudNotFoundException_when_codSolicitudNull_or_idNotExists() {
        Assertions.assertThrows(EstadoSolicitudNotFoundException.class, () -> {
            int id = -43;
            String estado = solicitudService.verEstadoSolicitud(id);
        });
    }

    @Nested
    class TestsCancelarSolicitudRenting {
        @Test
        public void cancelarSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {
            Assertions.assertThrows(SolicitudRentingNotFoundException.class, () -> {
                int id = -10;
                solicitudService.cancelarSolicitud(id);
            });
        }
    }
}
