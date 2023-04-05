package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.DenyRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.DenyRulesServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class denyRulesServiceTests {

    DenyRulesService service;


    SolicitudRenting solicitud;

    Renta renta;

    @BeforeEach
    public void setUp() throws ParseException {



        this.solicitud = this.createSolicitudMock();
        this.renta = this.createRentaMock();
        this.service = new DenyRulesServicesImpl();
    }

    private SolicitudRenting createSolicitudMock() {
        SolicitudRenting solicitud = new SolicitudRenting();
        Persona persona = new Persona();
        persona.setPersonaId(104);
        persona.setNombre("John");
        persona.setApellido1("Doe");
        persona.setApellido2("Doe");
        persona.setNacionalidad("ES");
        persona.setScoring(750);
        solicitud.setPersona(persona);
        solicitud.setInversion(10000);
        solicitud.setCuota(500);
        solicitud.setNumVehiculos(1);
        solicitud.setPlazo(36);
        return solicitud;
    }

    private Renta createRentaMock() throws ParseException {
        Renta renta = new Renta();
        Persona persona = new Persona();
        persona.setNombre("John");
        persona.setApellido1("Doe");
        persona.setApellido2("Doe");
        persona.setNacionalidad("ES");
        persona.setScoring(750);
        renta.setImporte(100000);
        renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        return renta;
    }

    @Test
    public void validateClientAge_shouldBeTrue_whenMinor18() throws ParseException {

        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2016"));
        boolean validationClientAge = service.validateClientAge(solicitud);
        Assertions.assertTrue(validationClientAge);
    }

    @Test
    public void validateClientAge_shouldBeFalse_whenMajor18() throws ParseException {

        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        boolean validationClientAge = service.validateClientAge(solicitud);
        Assertions.assertFalse(validationClientAge);
    }

    @Test
    public void validateClientAge_shouldBeFalse_whenNull() {
        this.solicitud.getPersona().setFechaNacimiento(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            boolean validationClientAge = service.validateClientAge(solicitud);
            Assertions.assertFalse(validationClientAge);
        });
    }

    @Test
    public void validateClientAge_shouldBeTrue_whenisEmpty() throws ParseException {
        String fechaNacimientoStr = "";
        if (fechaNacimientoStr.isEmpty()) {
            Assertions.assertThrows(NullPointerException.class, () -> {
                boolean validateClientAge = service.validateClientAge(solicitud);
                Assertions.assertFalse(validateClientAge);
            });
        } else {
            this.solicitud.getPersona().setFechaNacimiento(new Date(Long.MIN_VALUE));

        }
    }

    //validateScoringTitular
    @Test
    public void validateScoringTitular_shouldBeTrue_whenScoringMayororEqual6(){

        this.solicitud.getPersona().setScoring(10);
        boolean validateScoringTitular = service.validateScoringTitular(solicitud);
        Assertions.assertTrue(validateScoringTitular);
    }

    @Test
    public void validateScoringTitular_shouldBeTrue_whenScoringMinor6(){

        this.solicitud.getPersona().setScoring(3);
        boolean validateScoringTitular = service.validateScoringTitular(solicitud);
        Assertions.assertFalse(validateScoringTitular);
    }

    @Test
    public void validateClientAgePlusPlazo_shouldBeTrue_whenMayor80() throws ParseException {

        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2016"));
        this.solicitud.setPlazo(100);
        boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
        Assertions.assertTrue(validateClientAgePlusPlazo);
    }

    @Test
    public void validateClientAgePlusPlazo_shouldBeFalse_whenMinor80() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        this.solicitud.setPlazo(10);
        boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
        Assertions.assertFalse(validateClientAgePlusPlazo);
    }

    @Test
    public void validateClientAgePlusPlazo_shouldBeFalse_whenNull() {
        this.solicitud.getPersona().setFechaNacimiento(null);
        this.solicitud.setPlazo(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
            Assertions.assertFalse(validateClientAgePlusPlazo);
        });
    }

    @Test
    public void validateClientAgePlusPlazo_shouldBeTrue_whenisEmpty() throws ParseException {
        String fechaNacimientoStr = "";
        if (fechaNacimientoStr.isEmpty()) {
            Assertions.assertThrows(NullPointerException.class, () -> {
                boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
                Assertions.assertFalse(validateClientAgePlusPlazo);
            });
        } else {
            this.solicitud.getPersona().setFechaNacimiento(new Date(Long.MIN_VALUE));        }
    }

}
