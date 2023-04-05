package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;

public interface CodigoResolucionValidator {
    void validarCodResolucion(String codResolucion) throws EstadoSolicitudInvalidException;
}
