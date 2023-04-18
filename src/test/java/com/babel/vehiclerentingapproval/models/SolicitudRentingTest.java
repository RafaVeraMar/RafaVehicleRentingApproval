package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SolicitudRentingTest {
    SolicitudRenting solicitudRenting;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void solicitudRenting_shouldReturn_sameString() throws ParseException {
        SolicitudRenting solicitudRenting = createSolicitudRenting();
        Assertions.assertEquals("SolicitudRenting{solicitudId=1, persona=null, fechaSolicitud=null, numVehiculos=2, inversion=10.33, cuota=null, plazo=2, fechaInicioVigor=null, fechaResolucion=null, tipoResultadoSolicitud=null}",solicitudRenting.toString());
    }

    public SolicitudRenting createSolicitudRenting() {
        this.solicitudRenting = new SolicitudRenting();
        this.solicitudRenting.setSolicitudId(1);
        this.solicitudRenting.setPersona(null);
        this.solicitudRenting.setFechaSolicitud(null);
        this.solicitudRenting.setNumVehiculos(new BigInteger(String.valueOf(2)));
        this.solicitudRenting.setInversion(Float.parseFloat("10.33"));
        this.solicitudRenting.setPlazo(new BigInteger(String.valueOf(2)));
        this.solicitudRenting.setFechaInicioVigor(null);
        this.solicitudRenting.setFechaResolucion(null);
        this.solicitudRenting.setTipoResultadoSolicitud(null);
        return solicitudRenting;
    }


}
