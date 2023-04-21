package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.EmailService;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SolicitudRentingServiceImplTest {
    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;
    CodigoResolucionValidator codigoResolucionValidator;
    PersonaService personaService;
    PersonaMapper personaMapper;

    EmailService emailService;


    @BeforeEach
    void setUpAll() {
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        personaMapper = Mockito.mock(PersonaMapper.class);
        personaService = Mockito.mock(PersonaService.class);
        codigoResolucionValidator = new CodigoResolucionValidatorImpl(tipoResultadoSolicitudMapper);

        emailService = Mockito.mock(EmailService.class);

        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper, tipoResultadoSolicitudMapper, personaService, codigoResolucionValidator, personaMapper,emailService);


    }

    private TipoResultadoSolicitud creaTipoResultadoFicticia() {
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
        void verEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_or_idNotExists() {
            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(anyInt())).thenReturn(0);
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class, () -> {
                String estado = solicitudService.verEstadoSolicitud("1");
            });
        }

        @Test
        void verEstadoSolicitud_shouldNotThrow_EstadoSolicitudNotFoundException_when_codSolicitudNull_and_idNotExists() {
            Mockito.when(tipoResultadoSolicitudMapper.existeCodigoResolucion(anyInt())).thenReturn(1);
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(1);
            Mockito.when(tipoResultadoSolicitudMapper.getResultadoSolicitud(anyInt())).thenReturn(creaTipoResultadoFicticia());

            Assertions.assertDoesNotThrow(() -> {
                String estado = solicitudService.verEstadoSolicitud("1");
            });
        }

        @Test
        void verEstadoSolicitud_shouldThrow_EstadoSolicitudInvalidException_when_codSolicitudNotValid() {
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(0);
            Assertions.assertThrows(EstadoSolicitudInvalidException.class, () -> {
                codigoResolucionValidator.validarCodResolucion(anyString());
            });
        }

        @Test
        void verEstadoSolicitud_shouldNotThrow_EstadoSolicitudInvalidException_when_codSolicitudNotValid() {
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(1);

            Assertions.assertDoesNotThrow(() -> {
                codigoResolucionValidator.validarCodResolucion(anyString());
            });
        }

        @Test
        void verEstadoSolicitud_shouldThrow_IdIncorrectFormatException_when_idSolicitudisNotParseabletoInteger(){
            Assertions.assertThrows(IdIncorrectFormatException.class, () -> {
                String estado = solicitudService.verEstadoSolicitud("err");
            });
        }

    }

    @Nested
    class TestsVerSolicitudRenting {
        @Test
        void verSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);

            Assertions.assertThrows(SolicitudRentingNotFoundException.class, () -> {
                solicitudService.getSolicitudById(0);
            });
        }

        @Test
        void verSolicitudRenting_shouldNotThrow_SolicitudRentingNotFoundException_when_solicitudIdExists() {
            Mockito.when(solicitudRentingMapper.getSolicitudByID(anyInt())).thenReturn(new SolicitudRenting());

            Assertions.assertDoesNotThrow(() -> {
                solicitudService.getSolicitudById(0);
            });

        }
    }

    @Nested
    class TestsModificaEstadoSolicitud {
        @Test
        void modificaEstadoSolicitud_shouldThrow_EstadoSolicitudNotFoundException_when_codSolicitudNotExist() {

            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);
            Assertions.assertThrows(EstadoSolicitudNotFoundException.class, () -> {
                int id = 1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("WW");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });
        }

        @Test
        void modificaEstadoSolicitud_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {
            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);
            Assertions.assertThrows(SolicitudRentingNotFoundException.class, () -> {
                int id = -1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("AA");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });
        }

        @Test
        void modificaEstadoSolicitud_shouldThrow_SolicitudFailedSendingEmail_when_emailIsNull() throws ParseException {
            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);
            when(solicitudRentingMapper.getSolicitudByID(anyInt())).thenReturn(creaSolicitudFicticia());
            Mockito.when(personaMapper.getEmail(anyInt())).thenReturn(null);


            String dest = "blablagmail.com";
            String message = "Buenas";
            String asunto = "Importante";
            when(tipoResultadoSolicitudMapper.getEstadoSolicitud(anyInt())).thenReturn("");
            //Mockito.when(emailService.sendMail(message,dest,asunto)).thenReturn();
            Assertions.assertThrows(FailedSendingEmail.class, () -> {
                int id = 1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("AA");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });
        }

        @Test
        void modificaEstadoSolicitud_shouldThrow_SolicitudFailedSendingEmail_when_emailHasNotArroba() throws ParseException {
            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);
            when(solicitudRentingMapper.getSolicitudByID(anyInt())).thenReturn(creaSolicitudFicticia());
            Mockito.when(personaMapper.getEmail(anyInt())).thenReturn("blabla");

            String dest = "blablagmail.com";
            String message = "Buenas";
            String asunto = "Importante";
            when(tipoResultadoSolicitudMapper.getEstadoSolicitud(anyInt())).thenReturn("");
            //Mockito.when(emailService.sendMail(message,dest,asunto)).thenReturn();
            Assertions.assertThrows(FailedSendingEmail.class, () -> {
                int id = 1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("AA");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });

        }

        @Test
        void modificaEstadoSolicitud_shouldNotThrow_SolicitudRentingNotFoundException_when_emailExists() throws ParseException, MessagingException {
            Mockito.when(tipoResultadoSolicitudMapper.getListaEstados()).thenReturn(creaListaMock());
            when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);
            when(solicitudRentingMapper.getSolicitudByID(anyInt())).thenReturn(creaSolicitudFicticia());
            Mockito.when(personaMapper.getEmail(anyInt())).thenReturn("blabla@gmail.com");


            String dest = "blabla@gmail.com";
            String message = "Buenas";
            String asunto = "Importante";
            when(tipoResultadoSolicitudMapper.getEstadoSolicitud(anyInt())).thenReturn("");
            Mockito.when(emailService.sendMail(message, dest, asunto)).thenReturn(true);
            Assertions.assertDoesNotThrow(() -> {
                int id = 1;
                TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
                tipoResultadoSolicitud.setCodResultado("AA");
                tipoResultadoSolicitud.setDescripcion("");
                solicitudService.modificaEstadoSolicitud(id, tipoResultadoSolicitud);
            });
        }
    }

    private List<String> creaListaMock() {
        List<String> listaMock = new ArrayList<>();
        listaMock.add("AA");
        return listaMock;
    }


    @Nested
    class TestInsertSolicitudRenting {
        @Test
        void insertarSolicitudRenting_shouldThrow_PersonaNotFoundException_when_idPersonaNotExist(){
            when(personaService.existePersona(anyInt())).thenReturn(false);
            Assertions.assertThrows(PersonaNotFoundException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }
        @Test
        void insertarSolicitudRenting_shouldThrow_WrongLenghtFieldException_when_NumberVehiculosIsTooBig ( ) {
            when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(WrongLenghtFieldException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "123456789012345678901234567890123456789";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNullOrIsEmpty_when_NumberVehiculosIsNull ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setNumVehiculos(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_NumberVehiculosIsNegative ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "-123456789012345";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_NumberVehiculosIsZero ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                BigInteger bigInteger = BigInteger.ZERO;
                solicitudRenting.setNumVehiculos(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }


        @Test
        void addSolicitudRenting_shouldThrow_InputIsNullException_when_Inversion_IsNull ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void addSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Inversion_IsNegative ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(-14f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void addSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Inversion_IsZero ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setInversion(0f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNullException_when_Cuota_IsNull ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNullOrIsEmpty.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Cuota_IsNegative ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(-15f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_Cuota_IsZero ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setCuota(0f);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_WrongLenghtFieldException_when_NumberPlazoIsTooBig ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(WrongLenghtFieldException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "123456789012345678901234567890123456789";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_PlazoIsNegative ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                String numberString = "-12345678901234567890123456";
                BigInteger bigInteger = new BigInteger(numberString);
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldThrow_InputIsNegativeOrZeroException_when_PlazoIsZero ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(InputIsNegativeOrZeroException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                BigInteger bigInteger = BigInteger.ZERO;
                solicitudRenting.setPlazo(bigInteger);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldNotThrow_when_PlazoIsNull ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertDoesNotThrow(() -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                BigInteger bigInteger = BigInteger.ZERO;
                solicitudRenting.setPlazo(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }


        @Test
        void insertarSolicitudRenting_shouldThrow_DateIsBeforeException_when_FechaInicio_IsBefore_with_FechaResolucion ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertThrows(DateIsBeforeException.class, () -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("27-12-2023"));
                solicitudRenting.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("28-12-2023"));
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shouldNotThrow_DateIsBeforeException_when_FechaInicio_IsNotBefore_with_FechaResolucion ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertDoesNotThrow(() -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2023"));
                solicitudRenting.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("28-12-2023"));
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }

        @Test
        void insertarSolicitudRenting_shoulNotThrow_DateIsBeforeException_when_FechaInicio_IsNull ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertDoesNotThrow(() -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setFechaInicioVigor(null);
                solicitudRenting.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("28-12-2023"));
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }
        @Test
        void insertarSolicitudRenting_shouldNotThrow_DateIsBeforeException_when_FechaResolucion_IsNull ( ) {
            Mockito.when(personaService.existePersona(anyInt())).thenReturn(true);
            Assertions.assertDoesNotThrow(() -> {
                SolicitudRenting solicitudRenting = creaSolicitudFicticia();
                solicitudRenting.setFechaInicioVigor(new SimpleDateFormat("dd-MM-yyyy").parse("28-12-2023"));
                solicitudRenting.setFechaResolucion(null);
                solicitudService.addSolicitudRenting(solicitudRenting);
            });
        }
    }

    @Nested
    class TestsCancelarSolicitudRenting {
        @Test
        void cancelarSolicitudRenting_shouldThrow_SolicitudRentingNotFoundException_when_solicitudIdNotExists() {
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(0);

            Assertions.assertThrows(SolicitudRentingNotFoundException.class, () -> {
                solicitudService.cancelarSolicitud(0);
            });
        }

        @Test
        void cancelarSolicitudRenting_shouldNotThrow_SolicitudRentingNotFoundException_when_solicitudIdExists() throws ParseException {
            SolicitudRenting solicitudRenting = creaSolicitudFicticia();
            Mockito.when(solicitudRentingMapper.getSolicitudByID(anyInt())).thenReturn(solicitudRenting);
            Mockito.when(solicitudRentingMapper.existeSolicitud(anyInt())).thenReturn(1);

            Assertions.assertDoesNotThrow(() -> {
                solicitudService.cancelarSolicitud(1);
            });

        }
    }
}
