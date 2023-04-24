package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Esta clase define un método para validar un codigo de resolución.Implementa la interfaz
 * CodigoResolucionValidator y utiliza un objeto TipoResultadoSolicitudMapper
 * para validar los códigos de resolución
 * @author javier.serrano@babelgroup.com
 * @see CodigoResolucionValidator
 * @see TipoResultadoSolicitudMapper
 */
@Service
public class CodigoResolucionValidatorImpl implements CodigoResolucionValidator {
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;

    /**
     * Constructor de la clase. Recibe como argumento un objeto TipoResultadoSolicitudMapper
     * para realizar las validaciones de código de resolución.
     * @param tipoResultadoSolicitudMapper objeto TipoResultadoSolicitudMapper para realizar las validaciones
     */
    public CodigoResolucionValidatorImpl(TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper) {
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
    }

    /**
     * Método que valida un código de resolución para una solicitud.
     * @param codResolucion el código de resolución a validar
     * @throws EstadoSolicitudInvalidException si el código de resolución no es válido
     */
    @Override
    public void validarCodResolucion(String codResolucion) throws RequestApiValidationException {
        if(this.tipoResultadoSolicitudMapper.codigoValido(codResolucion) != 1){
            throw new EstadoSolicitudInvalidException(HttpStatus.BAD_REQUEST);
        }
    }
}
