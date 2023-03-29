package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.InversionIngresosMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.services.InversionIngresos;
import org.springframework.stereotype.Service;

@Service
public class InversionIngresosImpl implements InversionIngresos {


    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;

    public InversionIngresosImpl(InversionIngresosMapper inversionIngresosMapper) {
        this.inversionIngresosMapper = inversionIngresosMapper;
    }

    @Override
    public Boolean validateInversionIngresos(SolicitudRenting solicitudRenting) {

        if(this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudRenting.getSolicitudId())
                <= this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudRenting.getSolicitudId())){

            return true;

        }else{
            return false;
        }
    }
}

