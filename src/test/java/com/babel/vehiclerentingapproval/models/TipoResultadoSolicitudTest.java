package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TipoResultadoSolicitudTest {
    TipoResultadoSolicitud tipoResultadoSolicitud;

    @BeforeEach
    void setupAll() {

    }

    @Test
    public void tipoResultadoSolicitud_shouldReturn_sameString() {
        TipoResultadoSolicitud tipoResultadoSolicitud = createTipoResultadoSolicitud();
        Assertions.assertEquals(tipoResultadoSolicitud.toString(), "TipoResultadoSolicitud{codResultado='CA', descripcion='Cancelado'}");
    }

    public TipoResultadoSolicitud createTipoResultadoSolicitud() {
        this.tipoResultadoSolicitud= new TipoResultadoSolicitud();
        this.tipoResultadoSolicitud.setCodResultado("CA");
        this.tipoResultadoSolicitud.setDescripcion("Cancelado");
        return tipoResultadoSolicitud;
    }
}