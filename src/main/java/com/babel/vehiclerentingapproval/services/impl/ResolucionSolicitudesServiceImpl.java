package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ResolucionSolicitudesMapper;
import com.babel.vehiclerentingapproval.services.ResolucionSolicitudesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResolucionSolicitudesServiceImpl implements ResolucionSolicitudesService {

    ResolucionSolicitudesMapper resolucionSolicitudesMapper;

    public ResolucionSolicitudesServiceImpl(ResolucionSolicitudesMapper resolucionSolicitudesMapper) {
        this.resolucionSolicitudesMapper = resolucionSolicitudesMapper;
    }

    @Override
    public List<ResolucionSolicitud> listar() throws ResolucionSolicitudesNotFoundException {
        List<ResolucionSolicitud> lista = new ArrayList<ResolucionSolicitud>();
        lista=resolucionSolicitudesMapper.listar();
        existenSolicitudes(lista);
        return lista;
    }

    public void existenSolicitudes(List<ResolucionSolicitud> lista) throws ResolucionSolicitudesNotFoundException {
        if (lista.isEmpty()){
            throw new ResolucionSolicitudesNotFoundException();
        }
    }


}
