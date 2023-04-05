package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import org.springframework.stereotype.Service;

@Service
public class CodigoResolucionValidatorImpl implements CodigoResolucionValidator {
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;

    public CodigoResolucionValidatorImpl(TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper) {
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
    }

    @Override
    public void validarCodResolucion(String codResolucion) throws EstadoSolicitudInvalidException {
        if(this.tipoResultadoSolicitudMapper.codigoValido(codResolucion) != 1){
            throw new EstadoSolicitudInvalidException();
        }
    }
}
