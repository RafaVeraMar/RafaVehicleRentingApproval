package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface SolicitudRentingService {
    public void createRentingRequest(SolicitudRenting solicitudRenting);
    public void validateRentingRequest(SolicitudRenting solicitudRenting);

    public SolicitudRenting getSolicitudById(int it);
}
