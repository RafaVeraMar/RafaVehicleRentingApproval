package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface SolicitudRentingService {
	SolicitudRenting addSolicitudRenting(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, PersonaNotFoundException, InputIsNullOrIsEmpty, DateIsBeforeException;

    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException;
    public SolicitudRenting getSolicitudById(int it);

    void modificaSolicitud(Integer solicitudId, SolicitudRenting nuevoRenting);
    void cancelarSolicitud(int id);
}
