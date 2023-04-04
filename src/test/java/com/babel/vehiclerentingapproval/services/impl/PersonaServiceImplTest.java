package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
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

        direccionMapper = Mockito.mock(DireccionMapper.class);


        TelefonoMapper telefonoMapper = Mockito.mock(TelefonoMapper.class);
        TipoViaMapper tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        ProvinciaMapper provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        PaisMapper paisMapper = Mockito.mock(PaisMapper.class);


        personaService = new PersonaServiceImpl(direccionMapper,personaMapper,telefonoMapper,tipoViaMapper,provinciaMapper,paisMapper);

    }

    @Test
    public void modificarPersona_should_throwPersonaNotFoundException_when_personaNoExisteEnBaseDeDatos(){
        Assertions.assertThrows(PersonaNotFoundException.class,() ->{
            Persona persona = new Persona();
            persona.setPersonaId(100);
            personaService.modificarPersona(persona);
        });
    }

}
