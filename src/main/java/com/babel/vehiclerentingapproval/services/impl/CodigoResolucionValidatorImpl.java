package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
    public void validarCodResolucion(String codResolucion) {
        log.info("Entrando método validarCodResolucion");
        if(this.tipoResultadoSolicitudMapper.codigoValido(codResolucion) != 1){
            log.warn("Nueva excepción manejada, estado de solicitud inválido");
            throw new EstadoSolicitudInvalidException(HttpStatus.BAD_REQUEST);
        }
        log.info("Saliendo método validarCodResolucion sin errores");

    }
}
