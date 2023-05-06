package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RentaTest {
    Renta renta;

    @BeforeEach
    void setupAll() {

    }

    @Test
    void renta_shouldReturn_sameString() throws ParseException {
        Renta renta = createRenta();
        Assertions.assertEquals( "Renta{rentaId=1, persona=Persona{personaId=0, nombre='Juan', apellido1='Francés', apellido2='Atúñez', direccionDomicilioId=0, direccionNotificacionId=0, direccionDomicilioSameAsNotificacion=true, nif='null', fechaNacimiento=Mon Dec 29 00:00:00 CET 1980, nacionalidadIsoAlfa2=ES, scoring=0, fechaScoring=Fri Dec 29 00:00:00 CET 2000, telefonos=[TelefonoContacto{telefonoId=1, telefono='677645552'}], productosContratados=[ProductoContratado{idProductoContratado=1, idProducto=2, alias='Coche', importeNominal=100, fechaAlta=Sat Dec 30 00:00:00 CET 2000, fechaBaja=null, estado=null}], email='null'}, profesion=Profesion{profesionId=2, descripcion='Ingeniero'}, anio=2022, importeNeto=500.32, importeBruto=600.32, isCuentaPropia=1, iae='7873', cifEmpleador='null', fechaInicioEmpleo=Tue Dec 29 00:00:00 CET 1981}",renta.toString());
    }

    public Renta createRenta() throws ParseException {
        this.renta = new Renta();
        this.renta.setRentaId(1);
        this.renta.setPersona(createPersona());
        this.renta.setProfesion(createProfesion());
        this.renta.setAnio(2022);
        this.renta.setImporteNeto(Float.parseFloat("500.32"));
        this.renta.setImporteBruto(Float.parseFloat("600.32"));
        this.renta.setIsCuentaPropia(1);
        this.renta.setIae("7873");
        this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-1981"));;

        return renta;
    }

    public Profesion createProfesion() {
        Profesion profesion = new Profesion();
        profesion.setProfesionId(2);
        profesion.setDescripcion("Ingeniero");

        return profesion;
    }

    public Persona createPersona() throws ParseException {
        Persona persona = new Persona(persona.getPersonaId(), persona.getNombre(), persona.getEmail(), persona.getPassword());
        persona.setNombre("Juan");
        persona.setApellido1("Francés");
        persona.setApellido2("Atúñez");
        persona.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-1980"));

        Direccion domicilio = new Direccion();

        domicilio.setNombreCalle("Gran via");
        domicilio.setNumero("120");
        domicilio.setCodPostal("36201");
        domicilio.setMunicipio("Pontevedra");


        Direccion notificacion = new Direccion();

        domicilio.setNombreCalle("Plaza nueva");
        domicilio.setNumero("44");
        domicilio.setCodPostal("41001");
        domicilio.setMunicipio("Sevilla");


        persona.setDireccionDomicilio(domicilio);
        persona.setDireccionDomicilioSameAsNotificacion(true);
        persona.setDireccionNotificacion(notificacion);

        Pais nacionalidad = new Pais();
        nacionalidad.setIsoAlfa2("ES");
        persona.setNacionalidad(nacionalidad);

        persona.setFechaScoring(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2000"));

        List<TelefonoContacto> telefonos = new ArrayList<TelefonoContacto>();
        telefonos.add(new TelefonoContacto(1,"677645552"));
        persona.setTelefonos(telefonos);

        List<ProductoContratado> productosContratados = new ArrayList<ProductoContratado>();
        productosContratados.add(new ProductoContratado(1,2,"Coche",100,new SimpleDateFormat("dd-MM-yyyy").parse("30-12-2000")));
        persona.setProductosContratados(productosContratados);


        return persona;
    }
}
