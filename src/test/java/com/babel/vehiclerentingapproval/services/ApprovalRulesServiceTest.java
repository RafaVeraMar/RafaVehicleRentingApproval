package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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

    @BeforeEach
    public void setUp(){

        this.scoringRatingMapper = Mockito.mock((ScoringRatingMapper.class));
        this.employmentSeniorityMapper = Mockito.mock((EmploymentSeniorityMapper.class));
        this.inversionIngresosMapper = Mockito.mock((InversionIngresosMapper.class));
        this.personaMapper = Mockito.mock((PersonaMapper.class));
        this.rentaMapper = Mockito.mock((RentaMapper.class));
        this.salariedMapper = Mockito.mock((SalariedMapper.class));
        this.impagosCuotaMapper = Mockito.mock((ImpagosCuotaMapper.class));

        this.solicitud = this.createSolicitudMock();

        this.service = new ApprovalRulesServiceImpl(this.scoringRatingMapper, this.employmentSeniorityMapper, this.inversionIngresosMapper, this.personaMapper, this.rentaMapper,this.salariedMapper, this.impagosCuotaMapper);
    }

    private SolicitudRenting createSolicitudMock(){
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
    public void validateInversion_should_beTrue_when_80000(){
        this.solicitud.setInversion(90000);
        boolean validateInversion = service.validateNationality(this.solicitud);
        Assertions.assertTrue(validateInversion);
    }

    @Test
    public void validateInversion_should_beFalse_when_Not80000(){
        this.solicitud.setInversion(10000);
        boolean validateInversion = service.validateNationality(this.solicitud);
        Assertions.assertFalse(validateInversion);

    }

    @Test
    public void validateScoring_should_beTrue_when_minor5(){
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(1f);
        boolean validateScoring = service.validateScoringPersona(this.solicitud);
        Assertions.assertTrue(validateScoring);
    }
    @Test
    public void validateScoring_should_beFalse_when_major5(){
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(10f);
        boolean validateScoring = service.validateScoringPersona(this.solicitud);
        Assertions.assertFalse(validateScoring);
    }


    //test validateYearsExperience


}
