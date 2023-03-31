package com.babel.vehiclerentingapproval.services.preautomaticresults;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface DenyRulesService {



    Boolean validateClientAge(SolicitudRenting solicitudRenting);
    Boolean validateScoringTitular(SolicitudRenting solicitudRenting);
    Boolean validateClientAgePlusPlazo(SolicitudRenting solicitudRenting);
}
