package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private SolicitudRentingMapper solicitudRentingMapper;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
    }

    @Override
    public void createRentingRequest(SolicitudRenting solicitudRenting) {
        
    }

    @Override
    public void validateRentingRequest(SolicitudRenting solicitudRenting) {

    }

    @Override
    public String verEstadoSolicitud(int idSolicitud) {
        return "Estado de la solicitud con id " + idSolicitud + ": Demo";
    }
}
