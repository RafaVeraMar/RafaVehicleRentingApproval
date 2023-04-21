package com.babel.vehiclerentingapproval.models;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DireccionTest {
    Direccion direccion;

    @BeforeEach
    void setupAll(){

    }
    @Test
    void direccion_shouldReturn_sameString(){
        Direccion direccion = createDireccion();
        Assertions.assertEquals("Direccion{direccionId=1, tipoViaId=1', descripcionTipoVia=Via de ejemplo', nombreCalle='Calle ficticia', numero='22', piso='33', puerta='G', escalera='derecha', otroDato='Fachada amarilla', codPostal='41001', municipio='Sevilla', codProvincia=SE', codProvincia=Sevilla'}",direccion.toString());
    }

    public Direccion createDireccion(){
        this.direccion = new Direccion();
        direccion.setDireccionId(1);
        direccion.setTipoViaId(new TipoVia(1,"Via de ejemplo"));
        direccion.setNombreCalle("Calle ficticia");
        direccion.setNumero("22");
        direccion.setPiso("33");
        direccion.setPuerta("G");
        direccion.setEscalera("derecha");
        direccion.setOtroDato("Fachada amarilla");
        direccion.setCodPostal("41001");
        direccion.setMunicipio("Sevilla");
        direccion.setProvinciaCod(new Provincia("SE","Sevilla"));

        return direccion;
    }
}
