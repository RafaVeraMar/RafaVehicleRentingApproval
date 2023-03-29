package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.InputIsNull;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;

    PersonaMapper personaMapper;

    @BeforeEach
    void setUpAll(){
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        personaMapper = Mockito.mock(PersonaMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper,tipoResultadoSolicitudMapper, personaMapper);

    }
    @BeforeAll
    SolicitudRenting creaSolicitudFicticia(){
        SolicitudRenting solicitudFicticia = new SolicitudRenting();
        //TODO: hacer set a todo el objeto
        return solicitudFicticia;
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
            Assertions.assertThrows(SolicitudRentingNotFoundException.class,()->{
                int id = -10;
                solicitudService.getSolicitudById(id);
            });
        }
    }

    @Nested
    class TestInsertSolicitudRenting{
        @Test
        public void addSolicitudRenting_shouldThrow_InputIsNullException_when_Inversion_IsNull(){
            Assertions.assertThrows(InputIsNull.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(null);
                solicitudService.addSolicitudRenting(solicitudRenting);

            });


        }
        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNullException_when_Cuota_IsNull(){
            Assertions.assertThrows(InputIsNull.class, ()->{
                SolicitudRenting solicitudRenting = new SolicitudRenting();

            });


        }
        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNullException_when_Vehiculos_IsNull(){
            Assertions.assertThrows(InputIsNull.class, ()->{
                SolicitudRenting solicitudRenting = new SolicitudRenting();

            });


        }
    }

}
