package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.SolicitudRentingNotFoundException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException {
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(idSolicitud);

        if(codigoExiste == 0){
            throw new EstadoSolicitudNotFoundException();
        }
        String estado = tipoResultadoSolicitudMapper.getEstadoSolicitud(idSolicitud);
        return "Estado de la solicitud con id " + idSolicitud + ": " + estado;
    }

    public SolicitudRenting getSolicitudById(int id) throws SolicitudRentingNotFoundException {
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);

        if (solicitudRenting == null){
            throw new SolicitudRentingNotFoundException();
        }
        return solicitudRenting;
    }

    @Override
    public void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws EstadoSolicitudNotFoundException, SolicitudRentingNotFoundException {

        getSolicitudById(solicitudId);

        List<String> listaEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        if(!listaEstados.contains(nuevoEstado.getCodResultado())){
                throw new EstadoSolicitudNotFoundException();
        }
        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId,nuevoEstado);
    }
    @Override
    public List<String> getListaEstados() {
        List<String> listaEstados =  this.tipoResultadoSolicitudMapper.getListaEstados();
        return listaEstados;
    }

}
