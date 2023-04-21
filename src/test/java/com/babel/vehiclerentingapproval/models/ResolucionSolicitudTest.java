package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ResolucionSolicitudTest {
    ResolucionSolicitud resolucionSolicitud;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void resolucionSolicitud_shouldReturn_sameString() throws ParseException {
        ResolucionSolicitud resolucionSolicitud = createResolucionSolicitud();
        Assertions.assertEquals( "ResolucionSolicitud{codigoResultado='CA', descripcion='Cancelado'}",resolucionSolicitud.toString());
    }

    public ResolucionSolicitud createResolucionSolicitud(){
        this.resolucionSolicitud=new ResolucionSolicitud();
        this.resolucionSolicitud.setCodigoResultado("CA");
        this.resolucionSolicitud.setDescripcion("Cancelado");

        return resolucionSolicitud;
    }
}