package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RentaFoundException;
import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Profesion;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentaServiceImplTest {
    RentaService rentaService;
    RentaMapper rentaMapper;
    ProfesionService profesionService;
    PersonaService personaService;



    @BeforeEach
    void setUpAll() throws ProfesionNotFoundException {

        ProfesionMapper profesionMapper = Mockito.mock(ProfesionMapper.class);
        when(profesionMapper.existeProfesion(100)).thenReturn(0);
        when(profesionMapper.existeProfesion(1)).thenReturn(1);



        rentaMapper = Mockito.mock(RentaMapper.class);
        when(rentaMapper.existeRenta(1)).thenReturn(1);
        when(rentaMapper.existeRenta(100)).thenReturn(0);

        PersonaMapper personaMapper = Mockito.mock(PersonaMapper.class);
        when(personaMapper.existePersona(100)).thenReturn(0);
        when(personaMapper.existePersona(1)).thenReturn(1);

        DireccionMapper direccionMapper = Mockito.mock(DireccionMapper.class);
        TelefonoMapper telefonoMapper = Mockito.mock(TelefonoMapper.class);
        TipoViaMapper tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        ProvinciaMapper provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        PaisMapper paisMapper = Mockito.mock(PaisMapper.class);


        personaService = new PersonaServiceImpl(direccionMapper,personaMapper,telefonoMapper,tipoViaMapper,provinciaMapper,paisMapper);
        profesionService = new ProfesionServiceImpl(profesionMapper);


        rentaService = new RentaServiceImpl(rentaMapper,personaService, profesionService);
    }

    @Test
    public void addRenta_should_throwProfesionNotFoundException_when_profesionIdNoExiste(){
        Assertions.assertThrows(ProfesionNotFoundException.class,() ->{
            Renta renta = createRenta();

            Profesion profesion = createProfesion();
            profesion.setProfesionId(100);
            renta.setProfesion(profesion);


            this.rentaService.addRenta(renta);
        });
    }


    @Test
    public void addRenta_should_throwPersonaNotFoundException_when_personaIdNoExiste(){
        Assertions.assertThrows(PersonaNotFoundException.class,() ->{
            Renta renta = createRenta();
            Persona persona = createPersona();

            Profesion profesion = createProfesion();
            renta.setProfesion(profesion);
            persona.setPersonaId(100);


            this.rentaService.addRenta(renta);
        });
    }

    @Test
    public void addRenta_should_throwRentaFoundException_when_rentaIdExiste(){
        Assertions.assertThrows(RentaFoundException.class,() ->{
            Renta renta = createRenta();
            Persona persona = createPersona();
            Profesion profesion = createProfesion();

            renta.setProfesion(profesion);
            persona.setPersonaId(1);
            renta.setPersona(persona);
            renta.setRentaId(1);


            this.rentaService.addRenta(renta);
        });
    }



    private Renta createRenta() throws ParseException {
        Renta renta = new Renta();


        renta.setPersona(this.createPersona());
        renta.setProfesion(createProfesion());
        renta.setAnio(2023);
        renta.setImporteBruto(Float.parseFloat("632.44"));
        renta.setImporteNeto(Float.parseFloat("500.32"));
        renta.setIsCuentaPropia(1);
        renta.setIae("12");
        renta.setCifEmpleador("12345678F");
        renta.setFechaInicioEmpleo(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-13"));

        return renta;
    }


    private Profesion createProfesion(){
        Profesion profesion = new Profesion(1,null);
        return profesion;
    }

    private Persona createPersona() throws ParseException {
        Persona persona=new Persona();
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
