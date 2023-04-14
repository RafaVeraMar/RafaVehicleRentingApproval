package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;

import java.util.List;
/**
 * Esta interfaz define un método para recuperar la lista de codigos de resoluciones de solicitudes de la base de datos. .
 * @author andres.guijarro@babelgroup.com
 */
public interface ResolucionSolicitudesService {
    /**
     * Método que recupera los codigos de resolucion de solicitudes.
     * @throws ResolucionSolicitudesNotFoundException si no existe ninguna
     */
    public List<ResolucionSolicitud> getTipoResolucionesSolicitudes() throws ResolucionSolicitudesNotFoundException;
}
