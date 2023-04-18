package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TipoViaTest {
    TipoVia tipoVia;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void tipoVia_shouldReturn_sameString() {
        TipoVia tipoVia = createTipoVia();
        Assertions.assertEquals( "TipoVia{tipoViaId=2, descripcion='Calle'}",tipoVia.toString());
    }

    public TipoVia createTipoVia() {
        this.tipoVia= new TipoVia();
        this.tipoVia.setTipoViaId(2);
        this.tipoVia.setDescripcion("Calle");

        return tipoVia;
    }
}
