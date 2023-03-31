package com.babel.vehiclerentingapproval.services.impl;

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


}
