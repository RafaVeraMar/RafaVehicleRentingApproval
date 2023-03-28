package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFound;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private SolicitudRentingMapper solicitudRentingMapper;
    private TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper,TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
    }

    @Override
    public void createRentingRequest(SolicitudRenting solicitudRenting) {
        
    }

    @Override
    public void validateRentingRequest(SolicitudRenting solicitudRenting) {

    }

    @Override
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFound {
        SolicitudRenting solicitud = getSolicitudById(idSolicitud);
        Integer codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(solicitud.getTipoResultadoSolicitud().getCodResultado());

        if(codigoExiste == null){
            throw new EstadoSolicitudNotFound();
        }
        String estado = tipoResultadoSolicitudMapper.getEstadoSolicitud("XX");
        return "Estado de la solicitud con id " + idSolicitud + ": " + estado;
    }
    public SolicitudRenting getSolicitudById(int id) {
        return this.solicitudRentingMapper.getSolicitudByID(id);
    }

}
