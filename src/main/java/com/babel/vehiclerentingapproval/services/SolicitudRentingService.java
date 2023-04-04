package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;

import java.util.List;

public interface SolicitudRentingService {
	SolicitudRenting addSolicitudRenting(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, PersonaNotFoundException, InputIsNullOrIsEmpty, DateIsBeforeException;

    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException;
    public SolicitudRenting getSolicitudById(int it);

    void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws EstadoSolicitudNotFoundException, SolicitudRentingNotFoundException;
    public List<String> getListaEstados();

    void cancelarSolicitud(int id);
}
