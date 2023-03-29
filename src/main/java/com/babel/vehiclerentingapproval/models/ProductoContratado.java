package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.*;
import java.util.Date;

public class ProductoContratado {
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    private int idProductoContratado;
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    private int idProducto;
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String alias;
    @NotNull
    @NotEmpty
    @NotBlank
    private int importeNominal;
    @PastOrPresent
    private Date fechaAlta;
    private Date fechaBaja;

    public ProductoContratado(int idProductoContratado, int idProducto, String alias, int importeNominal, Date fechaAlta) {
        this.idProductoContratado = idProductoContratado;
        this.idProducto = idProducto;
        this.alias = alias;
        this.importeNominal = importeNominal;
        this.fechaAlta = fechaAlta;
    }

    public int getIdProductoContratado() {
        return idProductoContratado;
    }

    public void setIdProductoContratado(int idProductoContratado) {
        this.idProductoContratado = idProductoContratado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getImporteNominal() {
        return importeNominal;
    }

    public void setImporteNominal(int importeNominal) {
        this.importeNominal = importeNominal;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}
