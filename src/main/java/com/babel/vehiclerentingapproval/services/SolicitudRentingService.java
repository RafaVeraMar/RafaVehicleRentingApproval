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

    /**
     * Método que devuelve el estado de una solicitud
     * @param idSolicitud ID de la solicitud de renting
     * @return String con el estado de la solicitud
     * @throws RequestApiValidationException si la id de la solicitud no existe, el codigo de resolucion es nulo, o no es valido
     * @see RequestApiValidationException
     */
    public String verEstadoSolicitud (int idSolicitud) throws RequestApiValidationException;

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

    /**
     * Interfaz que se encarga de cancelar la solicitud asociada al id que se le pasa como parametro
     * @param id asociado a la solicitud que se quiere cancelar
     */
    void cancelarSolicitud (int id) throws RequestApiValidationException;
}
