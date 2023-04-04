package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ApprovalRulesServiceTest {

    ApprovalRulesService service;

    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;

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

        this.solicitud = this.createSolicitudMock();
        this.renta = this.createRentaMock();
        this.service = new ApprovalRulesServiceImpl(this.scoringRatingMapper, this.employmentSeniorityMapper, this.inversionIngresosMapper, this.personaMapper, this.rentaMapper,this.salariedMapper, this.impagosCuotaMapper);
    }

    private SolicitudRenting createSolicitudMock(){
        SolicitudRenting solicitud = new SolicitudRenting();
        Persona persona = new Persona();
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
    public void validateNationality_should_beTrue_when_ES(){

        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertTrue(validationNationality);
    }

    @Test
    public void validateNationality_should_beFalse_when_NotES(){

        this.solicitud.getPersona().setNacionalidad("IT");
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertFalse(validationNationality);
    }

    @Test
    public void validateNationality_should_beFalse_when_Empty(){

        this.solicitud.getPersona().setNacionalidad("");
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertFalse(validationNationality);
    }

    @Test
    public void validateNationality_should_beFalse_when_Null(){

        this.solicitud.getPersona().setNacionalidad(null);
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertFalse(validationNationality);
    }


    //test validateInversion
    @Test
    public void validateInversion_should_beTrue_when_BiggerThan80000(){
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(90000f);

        boolean validateInversion = service.validateInversion(this.solicitud);
        Assertions.assertTrue(validateInversion);
    }

    @Test
    public void validateInversion_should_beFalse_when_NotBiggerThan80000(){
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(10000f);
        boolean validateInversion = service.validateInversion(this.solicitud);
        Assertions.assertFalse(validateInversion);

    }

    //test validateYearsExperience

    @Test
    public void validateYearsExperience_should_beTrue_when_yearsEmploymentBiggerThan3YearsExperience() throws ParseException {
        //(TO_DATE(CURRENT_DATE) - ra.FECHA_INICIO_EMPLEO)/365
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(10f);
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        boolean validateInversion = service.validateYearsExperience(this.solicitud);
        Assertions.assertTrue(validateInversion);
    }

    @Test
    public void validateYearsExperience_should_beTrue_when_yearsEmploymentNotBiggerThan3YearsExperience(){
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(1f);
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        boolean validateInversion = service.validateYearsExperience(this.solicitud);
        Assertions.assertFalse(validateInversion);
    }
}
