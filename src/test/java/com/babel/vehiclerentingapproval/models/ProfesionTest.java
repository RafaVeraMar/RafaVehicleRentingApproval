package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProfesionTest {
    Profesion profesion;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void profesion_shouldReturn_sameString() {
        Profesion profesion = createProfesion();
        Assertions.assertEquals( "Profesion{profesionId=2, descripcion='Ingeniero'}",profesion.toString());
    }

    public Profesion createProfesion() {
        this.profesion = new Profesion();
        this.profesion.setProfesionId(2);
        this.profesion.setDescripcion("Ingeniero");

        return profesion;
    }
}
