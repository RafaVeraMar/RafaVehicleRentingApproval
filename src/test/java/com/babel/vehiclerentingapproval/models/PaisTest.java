package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaisTest {
    Pais pais;

    @BeforeEach
    void setupAll() {

    }

    @Test
    public void pais_shouldReturn_sameString() {
        Pais pais = createPais();
        Assertions.assertEquals(pais.toString(), "Pais{isoAlfa_2='1', isoNum_3=2, isoAlfa_3='ES', nombre='España', orden=4}");
    }

    public Pais createPais() {
        this.pais = new Pais();
        pais.setIsoAlfa_2("1");
        pais.setIsoNum_3(2);
        pais.setIsoAlfa_3("ES");
        pais.setNombre("España");
        pais.setOrden(4);

        return pais;
    }
}
