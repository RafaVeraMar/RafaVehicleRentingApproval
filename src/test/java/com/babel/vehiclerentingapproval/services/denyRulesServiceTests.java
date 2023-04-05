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
    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;
    private ApprovalGarantiaMapper garantiaMapper;

    SolicitudRenting solicitud;

    Renta renta;

    @BeforeEach
    public void setUp() throws ParseException {

        this.scoringRatingMapper = Mockito.mock((ScoringRatingMapper.class));
        this.employmentSeniorityMapper = Mockito.mock((EmploymentSeniorityMapper.class));
        this.inversionIngresosMapper = Mockito.mock((InversionIngresosMapper.class));
        this.personaMapper = Mockito.mock((PersonaMapper.class));
        this.rentaMapper = Mockito.mock((RentaMapper.class));
        this.salariedMapper = Mockito.mock((SalariedMapper.class));
        this.impagosCuotaMapper = Mockito.mock((ImpagosCuotaMapper.class));
        this.garantiaMapper = Mockito.mock((ApprovalGarantiaMapper.class));

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
    public void validateClientAge_shouldBeFalse_whenisEmpty() throws ParseException {
        String fechaNacimientoStr = ""; // cadena vacía
        if (fechaNacimientoStr.isEmpty()) {
            this.solicitud.getPersona().setFechaNacimiento(new Date(Long.MIN_VALUE));
        } else {
            this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse(fechaNacimientoStr));
        }
        boolean validationClientAge = service.validateClientAge(solicitud);
        Assertions.assertTrue(validationClientAge);
    }


}
