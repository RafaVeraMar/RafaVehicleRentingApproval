package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.CalculateAutomaticResult;
import com.babel.vehiclerentingapproval.services.preautomaticresults.DenyRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.CalculateAutomaticResultImpl;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.DenyRulesServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class preAutomaticResultService {
    ApprovalRulesService approvalRulesService;
    DenyRulesService denyRulesService;

    CalculateAutomaticResult calculateAutomaticResult;


    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;
    private ApprovalClienteMapper garantiaMapper;
    private AutomaticResultMapper automaticResultMapper;
    SolicitudRenting solicitud;

    Renta renta;

    @BeforeEach
    public void setUp() throws ParseException {


        this.solicitud = this.createSolicitudMock();
        this.renta = this.createRentaMock();
        this.denyRulesService = new DenyRulesServicesImpl();

        this.approvalRulesService = new ApprovalRulesServiceImpl(this.scoringRatingMapper,
                this.employmentSeniorityMapper, this.inversionIngresosMapper, this.personaMapper,
                this.rentaMapper, this.salariedMapper, this.impagosCuotaMapper, this.garantiaMapper);
        this.calculateAutomaticResult = new CalculateAutomaticResultImpl(this.denyRulesService,this.approvalRulesService,this.automaticResultMapper);
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
    public void validateFindAnyDeny_shouldBeTrue_whenAllFalse() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        this.solicitud.getPersona().setScoring(1);
        this.solicitud.setPlazo(10);
        boolean validateFindAnyDeny = this.calculateAutomaticResult.findAnyDeny(solicitud);

        Assertions.assertTrue(validateFindAnyDeny);

    }
    @Test
    public void validateFindAnyDeny_shouldBeFalse_whenAnyTrue() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2016"));
        this.solicitud.getPersona().setScoring(1);
        this.solicitud.setPlazo(10);
        boolean validateFindAnyDeny = this.calculateAutomaticResult.findAnyDeny(solicitud);

        Assertions.assertFalse(validateFindAnyDeny);

    }

}

