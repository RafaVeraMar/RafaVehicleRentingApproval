package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ProfesionTest {
    Profesion profesion;

    @BeforeEach
    void setupAll() {

    }

    @Test
    public void profesion_shouldReturn_sameString() {
        Profesion profesion = createProductoContratado();
        Assertions.assertEquals(profesion.toString(), "Profesion{profesionId=2, descripcion='Ingeniero'}");
    }

    public Profesion createProductoContratado() {
        this.profesion = new Profesion();
        this.profesion.setProfesionId(2);
        this.profesion.setDescripcion("Ingeniero");

        return profesion;
    }
}
