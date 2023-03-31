package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Profesion;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonaServiceImplTest {
    PersonaService personaService;
    DireccionMapper direccionMapper;
    PersonaMapper personaMapper;
    TelefonoMapper telefonoMapper;
    TipoViaMapper tipoViaMapper;
    ProvinciaMapper provinciaMapper;
    PaisMapper paisMapper;
    @BeforeEach
    void setUpAll(){
        personaMapper = Mockito.mock(PersonaMapper.class);
        direccionMapper = Mockito.mock(DireccionMapper.class);
        tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        telefonoMapper = Mockito.mock(TelefonoMapper.class);
        paisMapper = Mockito.mock(PaisMapper.class);
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
    public void viewPersonaProducto_should_throwPersonaNotFoundException_when_personaIdNoExiste(){
        personaService = Mockito.mock(PersonaService.class);
        when(personaService.existePersona(100)).thenReturn(false);

        Assertions.assertThrows(Exception.class,() ->{
            Persona persona = new Persona();
            persona.setPersonaId(100);
            this.personaService.viewPersonaProducto(persona.getPersonaId());
        });
    }

    @Test
    public void viewPersonaProducto_should_throwPersonaNotFoundException_when_personaIdExiste(){
        personaService = Mockito.mock(PersonaService.class);
        when(personaService.existePersona(1)).thenReturn(true);

        Assertions.assertThrows(Exception.class,() ->{
            Persona persona = new Persona();
            persona.setPersonaId(1);
            this.personaService.viewPersonaProducto(persona.getPersonaId());
        });
    }


}
