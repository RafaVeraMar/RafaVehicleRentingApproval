package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Profesion;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.DireccionMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ProfesionMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class RentaServiceImplTest {
    RentaService rentaService;
    PersonaMapper personaMapper;
    RentaMapper rentaMapper;
    ProfesionMapper profesionMapper;

    DireccionMapper direccionMapper;
    @BeforeEach
    void setUpAll(){
        personaMapper = Mockito.mock(PersonaMapper.class);
        direccionMapper = Mockito.mock(DireccionMapper.class);
        profesionMapper = Mockito.mock(ProfesionMapper.class);
        rentaMapper = Mockito.mock(RentaMapper.class);
        rentaService = new RentaServiceImpl(rentaMapper,profesionMapper,personaMapper);
    }

    @Test
    public void addRenta_should_throwProfesionNotFoundException_when_profesionIdNoExiste(){
        Assertions.assertThrows(Exception.class,() ->{
            Renta renta = createRenta();

            Profesion profesion = createProfesion();
            profesion.setProfesionId(9999);
            renta.setProfesion(profesion);


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
        Profesion profesion = new Profesion(1,"Informatico");
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
