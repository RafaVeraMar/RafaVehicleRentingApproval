package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.DireccionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonaServiceImplTest {
    PersonaMapper personaMapper;

    DireccionMapper direccionMapper;
    PersonaService personaService;

    @BeforeEach
    void setupAll(){
        personaMapper= Mockito.mock(PersonaMapper.class);
        when(personaMapper.existePersona(100)).thenReturn(0); //No existe la persona
        when(personaMapper.existePersona(1)).thenReturn(1); //Existe la persona

        direccionMapper = Mockito.mock(DireccionMapper.class);
        when(direccionMapper.existeDireccion(1)).thenReturn(0);

        TelefonoMapper telefonoMapper = Mockito.mock(TelefonoMapper.class);
        TipoViaMapper tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        ProvinciaMapper provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        PaisMapper paisMapper = Mockito.mock(PaisMapper.class);


        personaService = new PersonaServiceImpl(direccionMapper,personaMapper,telefonoMapper,tipoViaMapper,provinciaMapper,paisMapper);

    }

    @Test
    public void addPersona_should_throwRequiredMissingFieldException_when_nombreIsNull(){
        Assertions.assertThrows(Exception.class,() ->{
            Persona persona = new Persona();

            persona.setNombre(null);
            this.personaService.addPersona(persona);
        });
    }

    @Test
    public void modificarPersona_should_throwPersonaNotFoundException_when_personaNoExisteEnBaseDeDatos(){
        Assertions.assertThrows(PersonaNotFoundException.class,() ->{
            Persona persona = new Persona();
            persona.setPersonaId(100); //Persona no existente
            personaService.modificarPersona(persona);
        });
    }

    @Test
    public void modificarPersona_should_throwDireccionNotFoundException_when_direccionNoExisteEnBaseDeDatos(){
        Assertions.assertThrows(DireccionNotFoundException.class,() ->{
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
    public void modificarTelefono_should_throwPersonaNotFoundException_when_idPersonaNoExisteEnBaseDeDatos() {

        when(personaMapper.existePersona(anyInt())).thenReturn(0);
        Assertions.assertThrows(PersonaNotFoundException.class, ( ) -> {
            personaService.modificarTelefono(createPersona());
        });
    }

    private boolean existePersonaMockFalse(Integer personaId){
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


        Direccion notificacion = new Direccion();

        domicilio.setNombreCalle("Plaza nueva");
        domicilio.setNumero("44");
        domicilio.setCodPostal("41001");
        domicilio.setMunicipio("Sevilla");


        persona.setDireccionDomicilio(domicilio);
        persona.setDireccionDomicilioSameAsNotificacion(true);
        persona.setDireccionNotificacion(notificacion);

        return persona;
    }
}
