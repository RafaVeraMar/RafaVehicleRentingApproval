package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvinciaTest {
    Provincia provincia;

    @BeforeEach
    void setupAll() {

    }

    @Test
    public void profesion_shouldReturn_sameString() {
        Provincia provincia = createProvincia();
        Assertions.assertEquals(provincia.toString(), "Provincia{codProvincia='SE', nombre='SEVILLA'}");
    }

    public Provincia createProvincia() {
        this.provincia = new Provincia();
        this.provincia.setCodProvincia("SE");
        this.provincia.setNombre("SEVILLA");

        return provincia;
    }
}