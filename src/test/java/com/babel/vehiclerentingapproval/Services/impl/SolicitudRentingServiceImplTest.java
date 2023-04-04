package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import com.babel.vehiclerentingapproval.services.impl.CodigoResolucionValidatorImpl;
import com.babel.vehiclerentingapproval.services.impl.SolicitudRentingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;
    PersonaMapper personaMapper;
    CodigoResolucionValidator codigoResolucionValidator;

    @BeforeEach
    void setUpAll() {
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        personaMapper = Mockito.mock(PersonaMapper.class);
        codigoResolucionValidator = new CodigoResolucionValidatorImpl(tipoResultadoSolicitudMapper);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper,tipoResultadoSolicitudMapper, personaMapper,codigoResolucionValidator);

    }
    private TipoResultadoSolicitud creaTipoResultadoFicticia(){
        TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
        tipoResultadoSolicitud.setCodResultado("AA");
        tipoResultadoSolicitud.setCodResultado("Aprobada");
        return tipoResultadoSolicitud;
    }

    private SolicitudRenting creaSolicitudFicticia() throws ParseException {
        SolicitudRenting solicitudFicticia = new SolicitudRenting();
        Persona personaFicticia = new Persona();
        Direccion direccionFicticia = new Direccion();
        direccionFicticia.setDireccionId(1);
        direccionFicticia.setTipoViaId(2);
        direccionFicticia.setNombreCalle("Alosno");
        direccionFicticia.setNumero("5");
        direccionFicticia.setCodPostal("21006");
        direccionFicticia.setMunicipio("Huelva");
        direccionFicticia.setProvinciaCod("f5");
        personaFicticia.setPersonaId(1);
        personaFicticia.setNombre("Migue");
        personaFicticia.setApellido1("Estevez");
        personaFicticia.setDireccionDomicilio(direccionFicticia);
        personaFicticia.setDireccionNotificacion(direccionFicticia);
        personaFicticia.setDireccionDomicilioSameAsNotificacion(false);
        personaFicticia.setNif("4444444E");
        personaFicticia.setNacionalidad("ES");
        solicitudFicticia.setPersona(personaFicticia);
        solicitudFicticia.setSolicitudId(1);
        solicitudFicticia.setFechaSolicitud(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2023"));
        solicitudFicticia.setNumVehiculos(BigInteger.valueOf(3));
        solicitudFicticia.setInversion(40f);
        solicitudFicticia.setCuota(5f);
        solicitudFicticia.setPlazo(BigInteger.valueOf(4));
        solicitudFicticia.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2024"));
        solicitudFicticia.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2024"));
        return solicitudFicticia;
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
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(1);
            Mockito.when(tipoResultadoSolicitudMapper.getResultadoSolicitud(anyInt())).thenReturn(creaTipoResultadoFicticia());

            Assertions.assertDoesNotThrow(()->{
                String estado = solicitudService.verEstadoSolicitud(anyInt());
            });
        }
        @Test
        public void verEstadoSolicitud_shouldThrow_EstadoSolicitudInvalidException_when_codSolicitudNotValid(){
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(0);
            Assertions.assertThrows(EstadoSolicitudInvalidException.class,() ->{
                codigoResolucionValidator.validarCodResolucion(anyString());
            });
        }
        @Test
        public void verEstadoSolicitud_shouldNotThrow_EstadoSolicitudInvalidException_when_codSolicitudNotValid(){
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(1);

            Assertions.assertDoesNotThrow(()->{
                codigoResolucionValidator.validarCodResolucion(anyString());
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
    class TestInsertSolicitudRenting{


        @Test
        public void addSolicitudRenting_shouldThrow_InputIsNullException_when_Inversion_IsNull(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(1);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNullException_when_Cuota_IsNull(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(1);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }
        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNullException_when_Vehiculos_IsNull(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(1);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setNumVehiculos(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_DateIsBeforeException_when_FechaInicio_IsBefore_with_FechaResolucion(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(1);
            Assertions.assertThrows(DateIsBeforeException.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("27-12-2023"));
                solicitudRenting.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("28-12-2023"));
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_PersonaNotFoundException_when_PersonaId_NotExist(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(0);
            Assertions.assertThrows(PersonaNotFoundException.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_WrongLenghtFieldException_when_NumberVehiculosIsTooBig(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(1);
            Assertions.assertThrows(WrongLenghtFieldException.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "123456789012345678901234567890123456789";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_WrongLenghtFieldException_when_NumberPlazoIsTooBig(){
            Mockito.when(personaMapper.existePersona(1)).thenReturn(1);
            Assertions.assertThrows(WrongLenghtFieldException.class, ()->{
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "123456789012345678901234567890123456789";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }
    }

    
    @Nested
    class TestsCancelarSolicitudRenting {
        @Test
        public void cancelarSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);

            Assertions.assertThrows(SolicitudRentingNotFoundException.class, ()->{
                solicitudService.getSolicitudById(0);
            });
        }
        @Test
        public void cancelarSolicitudRenting_shouldNotThrow_SolicitudRentingNotFoundException_when_solicitudIdExists() {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(()->{
                solicitudService.getSolicitudById(0);
            });
        }
    }
}
