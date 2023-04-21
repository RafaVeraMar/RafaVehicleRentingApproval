package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TelefonoContactoTest {
    TelefonoContacto telefonoContacto;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void telefonoContacto_shouldReturn_sameString() {
        TelefonoContacto telefonoContacto = createTelefonoContacto();
        Assertions.assertEquals( "TelefonoContacto{telefonoId=1, telefono='666447337'}",telefonoContacto.toString());
    }

    public TelefonoContacto createTelefonoContacto() {
        this.telefonoContacto = new TelefonoContacto();
        this.telefonoContacto.setTelefonoId(1);
        this.telefonoContacto.setTelefono("666447337");
        return telefonoContacto;

    }
}

