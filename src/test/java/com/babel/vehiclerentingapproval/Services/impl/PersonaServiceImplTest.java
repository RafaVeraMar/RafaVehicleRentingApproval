package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.DireccionMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
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

        personaService = new PersonaServiceImpl(personaMapper,direccionMapper);
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
