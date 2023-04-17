package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;

/**
 * Esta interfaz define un método para validar un código de resolución de una solicitud.
 * El código de resolución es una cadena de caracteres que identifica el estado de una solicitud.
 * Si el código no es válido, se lanzará una excepción de tipo EstadoSolicitudInvalidException.
 * @author javier.serrano@babelgroup.com
 */
public interface CodigoResolucionValidator {
    /**
     * Método que valida un código de resolución para una solicitud.
     * @param codResolucion el código de resolución a validar
     * @throws EstadoSolicitudInvalidException si el código de resolución no es válido
     */
    void validarCodResolucion(String codResolucion) throws EstadoSolicitudInvalidException;
}
