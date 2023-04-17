package com.babel.vehiclerentingapproval.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class ProductoContratadoTest {
    ProductoContratado productoContratado;

    @BeforeEach
    void setupAll() {

    }

    @Test
    public void productoContratado_shouldReturn_sameString() throws ParseException {
        ProductoContratado productoContratado = createProductoContratado();
        Assertions.assertEquals(productoContratado.toString(), "ProductoContratado{idProductoContratado=10, idProducto=2, alias='Coche', importeNominal=100, fechaAlta=Mon Dec 29 00:00:00 CET 1980, fechaBaja=Tue Dec 30 00:00:00 CET 1980, estado=VIGENTE}");
    }

    public ProductoContratado createProductoContratado() throws ParseException {
        this.productoContratado = new ProductoContratado();
       this.productoContratado.setIdProductoContratado(10);
       this.productoContratado.setIdProducto(2);
       this.productoContratado.setAlias("Coche");
       this.productoContratado.setImporteNominal(100);
       this.productoContratado.setFechaAlta(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-1980"));
       this.productoContratado.setFechaBaja(new SimpleDateFormat("dd-MM-yyyy").parse("30-12-1980"));
       this.productoContratado.setEstado(EstadoProductoContratado.VIGENTE);


        return productoContratado;
    }
}