package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface SolicitudRentingService {

    SolicitudRenting addSolicitudRenting(SolicitudRenting solicitudRenting) throws RequestApiValidationException, PersonaNotFoundException;

}
