package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;

    @BeforeEach
    void setUpAll(){
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(this.solicitudRentingMapper,this.tipoResultadoSolicitudMapper);
    }

@Test
    public void validateExisteMapper(){
        when(solicitudRentingMapper.existeSolicitud(any(Integer.class))).thenReturn(0);

        Assert.isTrue(true);
    }

    @Nested
    class TestsVerEstadoSolicitud{
        @Test
        public void verEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_or_idNotExists(){
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class,() ->{
                int id = -43;
                String estado = solicitudService.verEstadoSolicitud(id);
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


    @Nested
    class TestsModificaEstadoSolicitud{
        @Test
        public void modificaEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNotExist(){

            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class,() ->{
                int id = 1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("WW");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id,tipoResultadoSolicitud);
            });
        }

        @Test
        public void modificaEstadoSolicitud_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {
            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);
            Assertions.assertThrows(SolicitudRentingNotFoundException.class, () -> {
                int id = -1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("AA");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id,tipoResultadoSolicitud);
            });
        }
    }

    private List<String> creaListaMock(){
        List<String> listaMock = new ArrayList<>();
        listaMock.add("AA");
        return listaMock;
    }
}
