package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;

import java.util.List;

public interface SolicitudRentingService {

    /**
     * Agrega una nueva solicitud de renting al sistema.
     * Valida la información proporcionada en el objeto SolicitudRenting antes de agregarlo.
     * Si la solicitud no cumple con los criterios de validación, se lanza una excepción de tipo RequestApiValidationException.
     *
     * @param solicitudRenting objeto SolicitudRenting que contiene la información de la solicitud de renting a agregar
     * @return SolicitudRenting agregada y actualizada con el ID generado en el sistema
     * @throws RequestApiValidationException si la solicitud no cumple con los criterios de validación
     */
    SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) throws RequestApiValidationException;

    public String verEstadoSolicitud (int idSolicitud) throws EstadoSolicitudNotFoundException, EstadoSolicitudInvalidException;

    public SolicitudRenting getSolicitudById (int it);

    void modificaEstadoSolicitud (Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws EstadoSolicitudNotFoundException, SolicitudRentingNotFoundException;

    public List<String> getListaEstados ( );

    void cancelarSolicitud (int id);
}
