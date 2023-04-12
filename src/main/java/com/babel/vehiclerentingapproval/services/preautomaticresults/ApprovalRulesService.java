package com.babel.vehiclerentingapproval.services.preautomaticresults;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface ApprovalRulesService {
    Boolean validateInversionIngresos(SolicitudRenting solicitudRenting);

    Boolean validateInversion(SolicitudRenting solicitudRenting);

    Boolean validateScoringPersona(SolicitudRenting solicitudRenting);

    Boolean validateImpagoCuota(SolicitudRenting solicitudRenting);

    Boolean validateCIFCliente(SolicitudRenting solicitudRenting);

    Boolean validateNationality(SolicitudRenting solicitudRenting);

    Boolean validateYearsExperience(SolicitudRenting solicitudRenting);

    Boolean validatefindPersonasByCodResultado(SolicitudRenting solicitudRenting);

    Boolean validateClienteNoAprobadoConGarantias(SolicitudRenting solicitudRenting);

    Boolean validateClienteNoRechazadoPreviamente(SolicitudRenting solicitudRenting);
    
}

