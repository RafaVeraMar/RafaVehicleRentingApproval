package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private final SolicitudRentingMapper solicitudRentingMapper;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
    }

    @Override
    public SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) {
         solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
         return solicitudRenting;
    }
}
