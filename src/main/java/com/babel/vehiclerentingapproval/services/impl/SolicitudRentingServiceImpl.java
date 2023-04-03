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
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException {
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(idSolicitud);

        if(codigoExiste == 0){
            throw new EstadoSolicitudNotFoundException();
        }
        String estado = tipoResultadoSolicitudMapper.getEstadoSolicitud(idSolicitud);
        return estado;
    }

    public SolicitudRenting getSolicitudById(int id) throws SolicitudRentingNotFoundException {
        int existe = this.solicitudRentingMapper.existeSolicitud(id);
        SolicitudRenting solicitudRenting;
        if (existe == 1) {
            solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        }else{
            throw new SolicitudRentingNotFoundException();
        }

        return solicitudRenting;
    }

    @Override
    public void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws SolicitudRentingNotFoundException, EstadoSolicitudNotFoundException{

        int booleanExisteCodigo = this.tipoResultadoSolicitudMapper.existeCodigoResolucion(solicitudId);
        if(booleanExisteCodigo == 0){
            throw new EstadoSolicitudNotFoundException();
        }
        SolicitudRenting solicitudRenting = getSolicitudById(solicitudId);
        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId,nuevoEstado);
    }
    @Override
    public List<String> getListaEstados() {
        List<String> listaEstados =  this.tipoResultadoSolicitudMapper.getListaEstados();
        return listaEstados;
    }

}
