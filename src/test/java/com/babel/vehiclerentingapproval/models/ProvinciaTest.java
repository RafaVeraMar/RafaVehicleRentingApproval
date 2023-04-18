package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProvinciaTest {
    Provincia provincia;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void profesion_shouldReturn_sameString() {
        Provincia provincia = createProvincia();
        Assertions.assertEquals("Provincia{codProvincia='SE', nombre='SEVILLA'}",provincia.toString());
    }

    public Provincia createProvincia() {
        this.provincia = new Provincia();
        this.provincia.setCodProvincia("SE");
        this.provincia.setNombre("SEVILLA");

        return provincia;
    }
}