package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.impl.PersonaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class PersonaControllerTest {
    PersonaMapper personaMapper;

    DireccionMapper direccionMapper;
    PersonaService personaService;

    @BeforeEach
    void setupAll() {
        personaMapper = Mockito.mock(PersonaMapper.class);
        when(personaMapper.existePersona(1)).thenReturn(1);
        direccionMapper = Mockito.mock(DireccionMapper.class);
        //when(direccionMapper.existeDireccion(1)).thenReturn(0);
        when(direccionMapper.existeDireccion(1)).thenReturn(1);

        TelefonoMapper telefonoMapper = Mockito.mock(TelefonoMapper.class);
        TipoViaMapper tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        ProvinciaMapper provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        PaisMapper paisMapper = Mockito.mock(PaisMapper.class);


        personaService = new PersonaServiceImpl(direccionMapper, personaMapper, telefonoMapper, tipoViaMapper, provinciaMapper, paisMapper);

    }

    private Persona personaficticia() throws ParseException {
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
        return personaFicticia;
    }

    @Test
    void testAddPersonaSuccess() throws Exception {
        PersonaService personaService = Mockito.mock(PersonaService.class);
        PersonaController personaController = new PersonaController(personaService);
        Persona persona = personaficticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.when(personaService.addPersona(persona)).thenReturn(persona);

        ResponseEntity response = personaController.addPersona(persona);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    

    @Test
    void testViewPersonaProductoSuccess() throws Exception {
        PersonaService personaService = Mockito.mock(PersonaService.class);
        PersonaController personaController = new PersonaController(personaService);
        Persona persona = personaficticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.when(personaService.viewPersonaProducto(persona.getPersonaId())).thenReturn(persona.getProductosContratados());

        ResponseEntity response = personaController.viewPersonaProducto(persona.getPersonaId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testModificarPersonaSuccess() throws Exception {
        PersonaService personaService = Mockito.mock(PersonaService.class);
        PersonaController personaController = new PersonaController(personaService);
        Persona persona = personaficticia();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.doNothing().when(personaService).modificarPersona(persona);


        ResponseEntity response = personaController.modificarPersona(persona, persona.getPersonaId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
