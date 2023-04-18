package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TipoResultadoSolicitudTest {
    TipoResultadoSolicitud tipoResultadoSolicitud;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void tipoResultadoSolicitud_shouldReturn_sameString() {
        TipoResultadoSolicitud tipoResultadoSolicitud = createTipoResultadoSolicitud();
        Assertions.assertEquals( "TipoResultadoSolicitud{codResultado='CA', descripcion='Cancelado'}",tipoResultadoSolicitud.toString());
    }

    public TipoResultadoSolicitud createTipoResultadoSolicitud() {
        this.tipoResultadoSolicitud= new TipoResultadoSolicitud();
        this.tipoResultadoSolicitud.setCodResultado("CA");
        this.tipoResultadoSolicitud.setDescripcion("Cancelado");
        return tipoResultadoSolicitud;
    }
}