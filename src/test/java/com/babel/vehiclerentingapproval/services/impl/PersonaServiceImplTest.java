package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonaServiceImplTest {
    PersonaMapper personaMapper;

    DireccionMapper direccionMapper;
    PersonaService personaService;
    PaisMapper paisMapper;
    TelefonoMapper telefonoMapper;

    @BeforeEach
    void setupAll ( ) {
        personaMapper = Mockito.mock(PersonaMapper.class);
        when(personaMapper.existePersona(100)).thenReturn(0); //No existe la persona
        when(personaMapper.existePersona(1)).thenReturn(1); //Existe la persona

        direccionMapper = Mockito.mock(DireccionMapper.class);
        when(direccionMapper.existeDireccion(1)).thenReturn(0);
        when(direccionMapper.existeDireccion(100)).thenReturn(1);

        telefonoMapper = Mockito.mock(TelefonoMapper.class);
        TipoViaMapper tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        ProvinciaMapper provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        paisMapper = Mockito.mock(PaisMapper.class);


        personaService = new PersonaServiceImpl(direccionMapper, personaMapper, telefonoMapper, tipoViaMapper, provinciaMapper, paisMapper);

    }

    @Test
    void addPersona_should_throwRequiredMissingFieldException_when_nombreIsNull ( ) {
        Assertions.assertThrows(Exception.class, ( ) -> {
            Persona persona = new Persona();

            persona.setNombre(null);
            this.personaService.addPersona(persona);
        });
    }

    @Test
    void addPersona_should_throwWrongLenghtFieldException_when_nombreIsBiggerThan50 ( ) {
        Assertions.assertThrows(WrongLenghtFieldException.class, ( ) -> {
            Persona persona = new Persona();

            persona.setNombre("nombre de persona muy largo de mas de cincuenta caracteres");
            this.personaService.addPersona(persona);
        });
    }


    @Test
    void addPersona_should_throwRequiredMissingFieldException_when_apellido1Null ( ) {
        Assertions.assertThrows(Exception.class, ( ) -> {
            Persona persona = new Persona();

            persona.setApellido1(null);
            this.personaService.addPersona(persona);
        });
    }

    @Test
    void addPersona_should_throwRequiredMissingFieldException_when_nifNull ( ) {
        Assertions.assertThrows(Exception.class, ( ) -> {
            Persona persona = new Persona();

            persona.setNif(null);
            this.personaService.addPersona(persona);
        });
    }


    @Test
    void addPersona_should_not_anyExceptions_when_personaDataIsCorrect ( ) {
        when(paisMapper.getPais(anyString())).thenReturn(new Pais("ES", 1, "ES", "ESPAÑA", 1));

        Assertions.assertDoesNotThrow(( ) -> {
            Persona persona = createPersona();

            this.personaService.addPersona(persona);
        });
    }

    @Test
    void modificarPersona_should_throwPersonaNotFoundException_when_personaNoExisteEnBaseDeDatos ( ) {
        Assertions.assertThrows(PersonaNotFoundException.class, ( ) -> {
            Persona persona = new Persona();
            persona.setPersonaId(100); //Persona no existente
            personaService.modificarPersona(persona);
        });
    }

    @Test
    void modificarPersona_should_throwDireccionNotFoundException_when_direccionNoExisteEnBaseDeDatos ( ) {
        Assertions.assertThrows(DireccionNotFoundException.class, ( ) -> {
            Persona persona = new Persona();

            Direccion direccion = new Direccion();
            direccion.setDireccionId(1); //Direccion no existente

            persona.setPersonaId(1); //Persona existente

            persona.setDireccionDomicilio(direccion);
            persona.setDireccionNotificacion(direccion);

            personaService.modificarPersona(persona);
        });
    }

    @Test
    void modificarPersona_should_not_throwAnyException_when_DatosSonCorrectos ( ) {
        when(direccionMapper.updateDireccion(any())).thenReturn(1);
        when(direccionMapper.existeDireccion(anyInt())).thenReturn((1));
        when(personaMapper.existePersona(anyInt())).thenReturn(1);

        Assertions.assertDoesNotThrow(( ) -> {
            Persona persona = createPersona();

            this.personaService.modificarPersona(persona);
        });

    }

    @Test
    void modificarTelefono_should_throwPersonaNotFoundException_when_idPersonaNoExisteEnBaseDeDatos ( ) {

        when(personaMapper.existePersona(anyInt())).thenReturn(0);

        Assertions.assertThrows(PersonaNotFoundException.class, ( ) -> {
            personaService.modificarTelefono(createPersona());
        });
    }

    @Test
    void modificarTelefono_should_not_throwAnyException_when_datosSonCorrectos ( ) {

        when(personaMapper.existePersona(anyInt())).thenReturn(1);

        List<TelefonoContacto> telefonoContactoList = new ArrayList<TelefonoContacto>();
        telefonoContactoList.add(new TelefonoContacto(1, "665453634"));
        when(telefonoMapper.listarTelefonos(anyInt())).thenReturn(telefonoContactoList);

        Assertions.assertDoesNotThrow(( ) -> {
            personaService.modificarTelefono(createPersona());
        });
    }

    @Test
    void addPersonaDireccion_should_not_ThrowException_when_direccionDomicilioDistintaNotificacion ( ) throws ParseException {

        when(personaMapper.existePersona(anyInt())).thenReturn(1);
        Persona persona = createPersona();
        persona.setDireccionDomicilioSameAsNotificacion(false);

        Direccion notificacion = new Direccion();

        notificacion.setNombreCalle("Avda constitucion");
        notificacion.setNumero("3");
        notificacion.setCodPostal("41002");
        notificacion.setMunicipio("SEVILLA");
        notificacion.setTipoViaId(new TipoVia(2, "Avenida"));
        notificacion.setProvinciaCod(new Provincia("SVQ", "SEVILLA"));

        persona.setDireccionNotificacion(notificacion);

        Assertions.assertDoesNotThrow(( ) -> {
            personaService.addPersona(persona);
        });
    }

    @Test
    void viewPersonaProducto_should_throwPersonaNotFoundException_when_personaNoExiste ( ) {

        when(personaMapper.existePersona(anyInt())).thenReturn(0);
        Assertions.assertThrows(PersonaNotFoundException.class, ( ) -> {
            personaService.viewPersonaProducto(createPersona().getPersonaId());
        });
    }

    @Test
    void viewPersonaProducto_should_not_throwThrowPersonaNotFoundException_when_personaExiste ( ) {

        when(personaMapper.existePersona(anyInt())).thenReturn(1);
        Assertions.assertDoesNotThrow(( ) -> {
            personaService.viewPersonaProducto(createPersona().getPersonaId());
        });
    }

    @Test
    void updateEstadoPersonaProducto_should_setEstadoVigente_when_fechaBajaIsNull ( ) throws ParseException {

        Persona persona = createPersona();
        persona.getProductosContratados().get(0).setFechaBaja(null);
        persona.getProductosContratados().get(0).setEstado(null);

        personaService.updateEstadoPersonaProducto(persona.getProductosContratados());
        Assertions.assertEquals(EstadoProductoContratado.VIGENTE, persona.getProductosContratados().get(0).getEstado());
    }

    @Test
    void updateEstadoPersonaProducto_should_setEstadoVencido_when_fechaBajaIsNotNull ( ) throws ParseException {

        Persona persona = createPersona();
        persona.getProductosContratados().get(0).setEstado(null);
        persona.getProductosContratados().get(0).setFechaBaja(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2000"));

        personaService.updateEstadoPersonaProducto(persona.getProductosContratados());
        Assertions.assertEquals(EstadoProductoContratado.VENCIDO, persona.getProductosContratados().get(0).getEstado());
    }

    @Test
    void validateNif_should_throwDniFoundException_when_dniExiste ( ) {
        when(personaMapper.existeNif(anyString())).thenReturn(1);

        Assertions.assertThrows(DniFoundException.class, ( ) -> {
            personaService.validateNif("1234567");
        });
    }

    @Test
    void existPerson_should_throwPersonaNotFoundException_when_idPersonaNegativo ( ) {


        Assertions.assertThrows(PersonaNotFoundException.class, ( ) -> {

            this.personaService.invalidPersonId(-1);
        });

    }

    @Test
    void existPerson_should_returnNull_when_idPersonaPositivo ( ) throws RequestApiValidationException {
        Assertions.assertEquals(null, personaService.invalidPersonId(10));

    }

    private boolean existePersonaMockFalse (Integer personaId) {
        return false;
    }

    private Persona createPersona ( ) throws ParseException {
        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido1("Francés");
        persona.setApellido2("Atúñez");
        persona.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-1980"));

        Direccion domicilio = new Direccion();

        domicilio.setNombreCalle("Gran via");
        domicilio.setNumero("120");
        domicilio.setCodPostal("36201");
        domicilio.setMunicipio("Pontevedra");
        domicilio.setTipoViaId(new TipoVia(1, "Calle"));
        domicilio.setProvinciaCod(new Provincia("SVQ", "Sevilla"));

        Direccion notificacion = new Direccion();

        notificacion.setNombreCalle("Plaza nueva");
        notificacion.setNumero("44");
        notificacion.setCodPostal("41001");
        notificacion.setMunicipio("Sevilla");
        notificacion.setTipoViaId(new TipoVia(1, "Calle"));
        notificacion.setProvinciaCod(new Provincia("SVQ", "Sevilla"));

        persona.setDireccionDomicilio(domicilio);
        persona.setDireccionDomicilioSameAsNotificacion(true);
        persona.setDireccionNotificacion(notificacion);

        persona.setNacionalidad(new Pais("ES", 1, "ES", "ESPAÑA", 1));

        List<ProductoContratado> productosContratados = new ArrayList<ProductoContratado>();
        productosContratados.add(new ProductoContratado(1, 2, "Coche", 100, new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2000")));
        persona.setProductosContratados(productosContratados);

        List<TelefonoContacto> telefonos = new ArrayList<TelefonoContacto>();
        telefonos.add(new TelefonoContacto(1, "677645552"));
        persona.setTelefonos(telefonos);


        return persona;
    }
}
