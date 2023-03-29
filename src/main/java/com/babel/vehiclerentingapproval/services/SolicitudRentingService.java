package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.exceptions.InputIsNull;
import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface SolicitudRentingService {
	SolicitudRenting addSolicitudRenting(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, PersonaNotFoundException, InputIsNull;

    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException;
    public SolicitudRenting getSolicitudById(int it);

    void modificaSolicitud(Integer solicitudId, SolicitudRenting nuevoRenting);
}
