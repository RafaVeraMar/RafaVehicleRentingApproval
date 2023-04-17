package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.DireccionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.impl.PersonaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void addPersona_should_throwRequiredMissingFieldException_when_apellido1Null(){
        Assertions.assertThrows(Exception.class, () ->{
            Persona persona = new Persona();

            persona.setApellido1(null);
            this.personaService.addPersona(persona);
        });
    }

    @Test
    public void addPersona_should_throwRequiredMissingFieldException_when_nifNull(){
        Assertions.assertThrows(Exception.class, () ->{
            Persona persona = new Persona();

            persona.setNif(null);
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

}
