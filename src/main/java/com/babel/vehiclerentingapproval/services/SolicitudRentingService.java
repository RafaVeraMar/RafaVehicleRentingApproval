package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;

import java.util.List;

public interface SolicitudRentingService {
    SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) throws RequestApiValidationException;

    public String verEstadoSolicitud (int idSolicitud) throws EstadoSolicitudNotFoundException, EstadoSolicitudInvalidException;

    public SolicitudRenting getSolicitudById (int it);

    void modificaEstadoSolicitud (Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws EstadoSolicitudNotFoundException, SolicitudRentingNotFoundException;

    public List<String> getListaEstados ( );

    /**
     * Interfaz que se encarga de cancelar la solicitud asociada al id que se le pasa como parametro
     * @param id asociado a la solicitud que se quiere cancelar
     */
    void cancelarSolicitud (int id);
}
