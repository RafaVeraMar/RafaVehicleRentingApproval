package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
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
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException {
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(idSolicitud);

        if(codigoExiste == 0){
            throw new EstadoSolicitudNotFoundException();
        }
        String estado = tipoResultadoSolicitudMapper.getEstadoSolicitud(idSolicitud);
        return estado;
    }
    public SolicitudRenting getSolicitudById(int id) throws SolicitudRentingNotFoundException {
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);

        if (solicitudRenting == null || id < 1){
            throw new SolicitudRentingNotFoundException();
        }
        return solicitudRenting;
    }

    @Override
    public void modificaSolicitud(Integer solicitudId, SolicitudRenting nuevoRenting) {
        this.solicitudRentingMapper.modificaSolicitud(solicitudId,nuevoRenting);
    }

}
