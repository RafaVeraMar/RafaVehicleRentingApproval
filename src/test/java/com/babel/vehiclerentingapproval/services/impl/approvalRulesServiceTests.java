package com.babel.vehiclerentingapproval.services.impl;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.InversionIngresosMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertTrue;




@SpringBootTest
public class ApprovalRulesServiceTests {
    SolicitudRenting solicitud;
    InversionIngresosMapper solicitudMapper;
    PersonaMapper personaMapper;
    ApprovalRulesService rules;



    @BeforeEach
    void setUpAll(){
        personaMapper = Mockito.mock(PersonaMapper.class);
        solicitudMapper = Mockito.mock(InversionIngresosMapper.class);
        solicitud = new SolicitudRenting(solicitudMapper, personaMapper);
    }

    @Autowired
    private ApprovalRulesService approvalRulesService;

    @Test
    public void testValidateInversionIngresos() {
        SolicitudRenting solicitud = createSolicitud();
        ApprovalRulesService approvalService = new ApprovalRulesServiceImpl();
        assertTrue(approvalService.validateInversionIngresos(solicitud));
    }

    @Test
    public void testValidateScoringPersona() {
        // TODO: Implementar el test para validateScoringPersona()
    }

    @Test
    public void testValidateYearsExperience() {
        // TODO: Implementar el test para validateYearsExperience()
    }

    @Test
    public void testValidateInversion() {
        // TODO: Implementar el test para validateInversion()
    }

    @Test
    public void testValidateImpagoCuota() {
        // TODO: Implementar el test para validateImpagoCuota()
    }

    @Test
    public void testValidateNationality() {
        // TODO: Implementar el test para validateNationality()
    }

    private static SolicitudRenting createSolicitud() {
        SolicitudRenting solicitud = new SolicitudRenting();
        Persona persona = new Persona();
        persona.setNombre("John");
        persona.setApellidos("Doe");
        persona.setNacionalidad("ES");
        persona.setScoring(750);
        persona.setEdad(35);
        persona.setExperienciaLaboral(10);

        solicitud.setPersona(persona);
        solicitud.setInversion(10000);
        solicitud.setCuota(500);
        solicitud.setNumVehiculos(1);
        solicitud.setPlazo(36);

        return solicitud;
    }
