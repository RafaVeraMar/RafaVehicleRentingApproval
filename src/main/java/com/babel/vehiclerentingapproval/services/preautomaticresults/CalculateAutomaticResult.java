package com.babel.vehiclerentingapproval.services.preautomaticresults;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface CalculateAutomaticResult {

    Boolean findAnyDeny(SolicitudRenting solicitudRenting);
    Boolean findAllApproval(SolicitudRenting solicitudRenting);
    Boolean findAnyApproval(SolicitudRenting solicitudRenting);

    void totalResult(SolicitudRenting solicitudRenting);

}
