package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaisTest {
    Pais pais;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void pais_shouldReturn_sameString() {
        Pais pais = createPais();
        Assertions.assertEquals("Pais{isoAlfa2='1', isoNum3=2, isoAlfa3='ES', nombre='España', orden=4}",pais.toString());
    }

    public Pais createPais() {
        this.pais = new Pais();
        pais.setIsoAlfa2("1");
        pais.setIsoNum3(2);
        pais.setIsoAlfa3("ES");
        pais.setNombre("España");
        pais.setOrden(4);

        return pais;
    }
}
