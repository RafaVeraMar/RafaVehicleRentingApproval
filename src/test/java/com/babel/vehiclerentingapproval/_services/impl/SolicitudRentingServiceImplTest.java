package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;
    PersonaMapper personaMapper;

    @BeforeEach
    void setUpAll ( ) {
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        personaMapper = Mockito.mock(PersonaMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper, tipoResultadoSolicitudMapper, personaMapper);

        personaMapper = Mockito.mock(PersonaMapper.class);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper, tipoResultadoSolicitudMapper, personaMapper);
    }

    private SolicitudRenting creaSolicitudFicticia ( ) throws ParseException {
        SolicitudRenting solicitudFicticia = new SolicitudRenting();
        Persona personaFicticia = new Persona();
        Direccion direccionFicticia = new Direccion();
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
    class TestsVerEstadoSolicitud {
        @Test
        public void verEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_or_idNotExists ( ) {
            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(anyInt())).thenReturn(0);
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class, ( ) -> {
                String estado = solicitudService.verEstadoSolicitud(anyInt());
            });
        }

        @Test
        public void verEstadoSolicitud_shouldNotThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_and_idNotExists ( ) {
            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(( ) -> {
                String estado = solicitudService.verEstadoSolicitud(anyInt());
            });
        }
    }

    @Nested
    class TestsVerSolicitudRenting {
        @Test
        public void verSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists ( ) {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);

            Assertions.assertThrows(SolicitudRentingNotFoundException.class, ( ) -> {
                solicitudService.getSolicitudById(0);
            });
        }

        @Test
        public void verSolicitudRenting_shouldNotThrow_SolicitudRentingNotFoundException_when_solicitudIdExists ( ) {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(( ) -> {
                solicitudService.getSolicitudById(0);
            });

        }
    }

    @Nested
    class TestsModificaEstadoSolicitud {
        @Test
        public void modificaEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNotExist ( ) {

            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class, ( ) -> {
                int id = 1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("WW");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });
        }

        @Test
        public void modificaEstadoSolicitud_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists ( ) {
            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);
            Assertions.assertThrows(SolicitudRentingNotFoundException.class, ( ) -> {
                int id = -1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("AA");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });
        }
    }

    private List<String> creaListaMock ( ) {
        List<String> listaMock = new ArrayList<>();
        listaMock.add("AA");
        return listaMock;
    }


    @Nested
    class TestInsertSolicitudRenting {

        @Test
        public void insertarSolicitudRenting_shouldThrow_WrongLenghtFieldException_when_NumberVehiculosIsTooBig ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(WrongLenghtFieldException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "123456789012345678901234567890123456789";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNullOrIsEmpty_when_NumberVehiculosIsNull ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setNumVehiculos(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_NumberVehiculosIsNegative ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "-123456789012345";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_NumberVehiculosIsZero ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                BigInteger bigInteger = BigInteger.ZERO;
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }


        @Test
        public void addSolicitudRenting_shouldThrow_InputIsNullException_when_Inversion_IsNull ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void addSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Inversion_IsNegative ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(-14f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void addSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Inversion_IsZero ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(0f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNullException_when_Cuota_IsNull ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Cuota_IsNegative ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(-15f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Cuota_IsZero ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(0f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_WrongLenghtFieldException_when_NumberPlazoIsTooBig ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(WrongLenghtFieldException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "123456789012345678901234567890123456789";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_PlazoIsNegative ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "-12345678901234567890123456";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        public void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_PlazoIsZero ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                BigInteger bigInteger = BigInteger.ZERO;
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }


        @Test
        public void insertarSolicitudRenting_shouldThrow_DateIsBeforeException_when_FechaInicio_IsBefore_with_FechaResolucion ( ) {
            Mockito.when(personaMapper.existePersona(anyInt())).thenReturn(1);
            Assertions.assertThrows(DateIsBeforeException.class, ( ) -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("27-12-2023"));
                solicitudRenting.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("28-12-2023"));
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }
    }

    @Nested
    class TestsCancelarSolicitudRenting {
        @Test
        public void cancelarSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists ( ) {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);

            Assertions.assertThrows(SolicitudRentingNotFoundException.class, ( ) -> {
                solicitudService.cancelarSolicitud(0);
            });
        }

        @Test
        public void cancelarSolicitudRenting_shouldNotThrow_SolicitudRentingNotFoundException_when_solicitudIdExists ( ) throws ParseException {
            SolicitudRenting solicitudRenting = creaSolicitudFicticia();
            Mockito.when(solicitudRentingMapper.getSolicitudByID(anyInt())).thenReturn(solicitudRenting);
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(( ) -> {
                solicitudService.cancelarSolicitud(1);
            });

        }
    }
}
