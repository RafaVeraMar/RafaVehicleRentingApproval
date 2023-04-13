package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;

import java.util.List;

/**
 * Esta clase SERVICE sirve como interfaz de las operaciones para hacer el CRUD (Crear, Ver, Modificar y Cancelar) de las Solicitudes de Renting
 *
 * @author miguel.sdela@babelgroup.com / javier.serrano@babelgroup.com / ramon.vazquez@babelgroup.com / alvaro.aleman@babelgroup.com / javier.roldan@babelgroup.com
 */
public interface SolicitudRentingService {
    SolicitudRenting addSolicitudRenting(SolicitudRenting solicitudRenting) throws RequestApiValidationException;

    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException, EstadoSolicitudInvalidException;

    /**
     * Método que devuelve un objeto SolicitudRenting encontrado por su ID
     *
     * @param it Es el ID de la solicitud que se quiere buscar
     * @return devuelve un objeto SolicitudRenting cuando la ha encontrado.
     * @throws RequestApiValidationException
     */
    public SolicitudRenting getSolicitudById(int it) throws RequestApiValidationException;

    void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws EstadoSolicitudNotFoundException, SolicitudRentingNotFoundException;

    public List<String> getListaEstados();

    void cancelarSolicitud(int id) throws RequestApiValidationException;
}
