package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ResolucionSolicitudesMapper;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta clase da soporte al servicio de resolucion de solicitudes. Proporciona un método para
 * la obtención del tipo de solicitudes
 *
 * @author andres.guijarro@babelgroup.com
 * @see ResolucionSolicitudesMapper
 */
@Service
public class ResolucionSolicitudesServiceImpl implements ResolucionSolicitudesService {

    ResolucionSolicitudesMapper resolucionSolicitudesMapper;

    public ResolucionSolicitudesServiceImpl (ResolucionSolicitudesMapper resolucionSolicitudesMapper) {
        this.resolucionSolicitudesMapper = resolucionSolicitudesMapper;
    }

    /**
     * Recupera los tipos de solicitudes existentes en la base de datos.
     *
     * @return Una lista de objetos de tipo ResolucionSolicitud con los datos de la base de datos
     * @throws ResolucionSolicitudesNotFoundException Si no se encuentra ningun datos de resolucion de solicitudes.
     * @see #existenSolicitudes()
     * @see ResolucionSolicitud
     */
    @Override
    public List<ResolucionSolicitud> getTipoResolucionesSolicitudes ( ) throws ResolucionSolicitudesNotFoundException {
        List<ResolucionSolicitud> lista;
        this.existenSolicitudes();
        lista = resolucionSolicitudesMapper.getTipoResolucionesSolicitudes();
        return lista;
    }

    /**
     * Comprueba si existe una persona con el identificador proporcionado. Si no existe, lanza la excepcion ResolucionSolicitudesNotFoundException
     *
     * @throws ResolucionSolicitudesNotFoundException si no se encuentran codigos de resolución de solicitudes en la base de datos.
     */
    private void existenSolicitudes ( ) throws RequestApiValidationException {
        if (resolucionSolicitudesMapper.getTipoResolucionesSolicitudes().isEmpty()) {
            throw new ResolucionSolicitudesNotFoundException(HttpStatus.NOT_FOUND);
        }
    }


}
