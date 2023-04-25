package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import com.babel.vehiclerentingapproval.services.RentaService;
import com.babel.vehiclerentingapproval.services.impl.PersonaServiceImpl;
import com.babel.vehiclerentingapproval.services.impl.ProfesionServiceImpl;
import com.babel.vehiclerentingapproval.services.impl.RentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

class RentaControllerTest {


    RentaMapper rentaMapper;

    RentaService rentaService;
    PersonaService personaService;

    ProfesionService profesionService;

    @BeforeEach
    void setupAll() {

        ProfesionMapper profesionMapper = Mockito.mock(ProfesionMapper.class);
        when(profesionMapper.existeProfesion(1)).thenReturn(1);

        rentaMapper = Mockito.mock(RentaMapper.class);
        when(rentaMapper.existeRenta(1)).thenReturn(1);

        PersonaMapper personaMapper = Mockito.mock(PersonaMapper.class);
        when(personaMapper.existePersona(1)).thenReturn(1);

        DireccionMapper direccionMapper = Mockito.mock(DireccionMapper.class);
        TelefonoMapper telefonoMapper = Mockito.mock(TelefonoMapper.class);
        TipoViaMapper tipoViaMapper = Mockito.mock(TipoViaMapper.class);
        ProvinciaMapper provinciaMapper = Mockito.mock(ProvinciaMapper.class);
        PaisMapper paisMapper = Mockito.mock(PaisMapper.class);


        personaService = new PersonaServiceImpl(direccionMapper, personaMapper, telefonoMapper, tipoViaMapper, provinciaMapper, paisMapper);
        profesionService = new ProfesionServiceImpl(profesionMapper);

        rentaService = new RentaServiceImpl(rentaMapper, personaService, profesionService);

    }

    private Renta createRenta() throws ParseException {
        Renta renta = new Renta();


        renta.setPersona(this.personaficticia());
        renta.setProfesion(this.createProfesion());
        renta.setAnio(2023);
        renta.setImporteBruto(Float.parseFloat("632.44"));
        renta.setImporteNeto(Float.parseFloat("500.32"));
        renta.setIsCuentaPropia(1);
        renta.setIae("12");
        renta.setCifEmpleador("12345678F");
        renta.setFechaInicioEmpleo(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-13"));

        return renta;
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

    private Profesion createProfesion() {
        Profesion profesion = new Profesion(1, null);
        return profesion;
    }

    @Test
    void testAddRentaSuccess() throws Exception {
        RentaService rentaService = Mockito.mock(RentaService.class);
        RentaController rentaController = new RentaController(rentaService);
        Renta renta = createRenta();

        // Configurar el comportamiento de personaService.addPersona()
        Mockito.when(rentaService.addRenta(renta)).thenReturn(renta);

        ResponseEntity response = rentaController.addRenta(renta);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
