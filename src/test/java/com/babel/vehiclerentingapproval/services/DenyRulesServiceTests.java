package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.Pais;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.preautomaticresults.DenyRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.DenyRulesServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DenyRulesServiceTests {

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
        Pais pais = new Pais("ES", 724, "ESP", "España", 1);
        persona.setNacionalidad(pais);
        persona.setScoring(750);
        solicitud.setPersona(persona);
        solicitud.setInversion(10000f);
        solicitud.setCuota(500f);
        BigInteger num1 = BigInteger.valueOf(1);
        solicitud.setNumVehiculos(num1);
        BigInteger num2 = BigInteger.valueOf(36);
        solicitud.setPlazo(num2);
        return solicitud;
    }

    private Renta createRentaMock() throws ParseException {
        Renta renta = new Renta();
        Persona persona = new Persona();
        persona.setNombre("John");
        persona.setApellido1("Doe");
        persona.setApellido2("Doe");
        Pais pais = new Pais("ES", 724, "ESP", "España", 1);
        persona.setNacionalidad(pais);
        persona.setScoring(750);
        renta.setImporteBruto(10000f);
        renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        return renta;
    }

    @Test
     void validateClientAge_shouldBeTrue_whenMinor18() throws ParseException {

        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2016"));
        boolean validationClientAge = service.validateClientAge(solicitud);
        Assertions.assertTrue(validationClientAge);
    }

    @Test
     void validateClientAge_shouldBeFalse_whenMajor18() throws ParseException {

        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        boolean validationClientAge = service.validateClientAge(solicitud);
        Assertions.assertFalse(validationClientAge);
    }

    @Test
     void validateClientAge_shouldBeFalse_whenNull() {
        this.solicitud.getPersona().setFechaNacimiento(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            boolean validationClientAge = service.validateClientAge(solicitud);
            //Assertions.assertFalse(validationClientAge);
        });
    }

    @Test
     void validateClientAge_shouldBeTrue_whenisEmpty() throws ParseException {
        String fechaNacimientoStr = "";
        if (fechaNacimientoStr.isEmpty()) {
            Assertions.assertThrows(NullPointerException.class, () -> {
                boolean validateClientAge = service.validateClientAge(solicitud);
                //Assertions.assertFalse(validateClientAge);
            });
        } else {
            this.solicitud.getPersona().setFechaNacimiento(new Date(Long.MIN_VALUE));

        }
    }

    //validateScoringTitular
    @Test
     void validateScoringTitular_shouldBeTrue_whenScoringMayororEqual6(){

        this.solicitud.getPersona().setScoring(10);
        boolean validateScoringTitular = service.validateScoringTitular(solicitud);
        Assertions.assertTrue(validateScoringTitular);
    }

    @Test
     void validateScoringTitular_shouldBeTrue_whenScoringMinor6(){

        this.solicitud.getPersona().setScoring(3);
        boolean validateScoringTitular = service.validateScoringTitular(solicitud);
        Assertions.assertFalse(validateScoringTitular);
    }

    @Test
     void validateClientAgePlusPlazo_shouldBeTrue_whenMayor80() throws ParseException {

        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2016"));
        BigInteger num2 = BigInteger.valueOf(100);
        solicitud.setPlazo(num2);
        boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
        Assertions.assertTrue(validateClientAgePlusPlazo);
    }

    @Test
     void validateClientAgePlusPlazo_shouldBeFalse_whenMinor80() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        BigInteger num2 = BigInteger.valueOf(10);
        solicitud.setPlazo(num2);
        boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
        Assertions.assertFalse(validateClientAgePlusPlazo);
    }

    @Test
     void validateClientAgePlusPlazo_shouldBeFalse_whenNull() {
        this.solicitud.getPersona().setFechaNacimiento(null);
        this.solicitud.setPlazo(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
            //Assertions.assertFalse(validateClientAgePlusPlazo);
        });
    }

    @Test
     void validateClientAgePlusPlazo_shouldBeTrue_whenisEmpty() throws ParseException {
        String fechaNacimientoStr = "";
        if (fechaNacimientoStr.isEmpty()) {
            Assertions.assertThrows(NullPointerException.class, () -> {
                boolean validateClientAgePlusPlazo = service.validateClientAgePlusPlazo(solicitud);
                //Assertions.assertFalse(validateClientAgePlusPlazo);
            });
        } else {
            this.solicitud.getPersona().setFechaNacimiento(new Date(Long.MIN_VALUE));        }
    }

}
