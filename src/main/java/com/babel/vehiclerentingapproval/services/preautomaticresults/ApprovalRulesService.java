package com.babel.vehiclerentingapproval.services.preautomaticresults;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;

import java.util.Optional;

public interface ApprovalRulesService {
    Boolean validateInversionIngresos(SolicitudRenting solicitudRenting);

    Boolean validateInversion(SolicitudRenting solicitudRenting);

    Boolean validateScoringPersona(SolicitudRenting solicitudRenting);

    Boolean validateImpagoCuota(SolicitudRenting solicitudRenting);

    Boolean validateCIFCliente(SolicitudRenting solicitudRenting);

    Boolean validateNationality(SolicitudRenting solicitudRenting);

    Boolean validateYearsExperience(SolicitudRenting solicitudRenting);

     Optional<Persona> validatefindPersonasByCodResultado(SolicitudRenting solicitudRenting);

}

