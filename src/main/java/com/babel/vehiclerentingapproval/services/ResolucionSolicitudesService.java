package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.ResolucionSolicitudesNotFoundException;
import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;

import java.util.List;

public interface ResolucionSolicitudesService {
    public List<ResolucionSolicitud> listar() throws ResolucionSolicitudesNotFoundException;
    public void existenSolicitudes(List<ResolucionSolicitud> lista) throws ResolucionSolicitudesNotFoundException;
}
