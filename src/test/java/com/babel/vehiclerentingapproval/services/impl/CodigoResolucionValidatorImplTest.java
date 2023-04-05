package com.babel.vehiclerentingapproval.Services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudInvalidException;
import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.impl.CodigoResolucionValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class CodigoResolucionValidatorImplTest {
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    CodigoResolucionValidator codigoResolucionValidator;
    @BeforeEach
    void setUpAll(){
        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        codigoResolucionValidator = new CodigoResolucionValidatorImpl(tipoResultadoSolicitudMapper);
    }

    @Nested
    class TestsvalidarCodResolucion{
        @Test
        public void verEstadoSolicitud_shouldThrow_EstadoSolicitudInvalidException_when_codSolicitudNotValid(){
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(0);
            Assertions.assertThrows(EstadoSolicitudInvalidException.class,() ->{
                codigoResolucionValidator.validarCodResolucion(anyString());
            });
        }
        @Test
        public void verEstadoSolicitud_shouldNotThrow_EstadoSolicitudInvalidException_when_codSolicitudNotValid(){
            Mockito.when(tipoResultadoSolicitudMapper.codigoValido(anyString())).thenReturn(1);

            Assertions.assertDoesNotThrow(()->{
                codigoResolucionValidator.validarCodResolucion(anyString());
            });
        }
    }
}
