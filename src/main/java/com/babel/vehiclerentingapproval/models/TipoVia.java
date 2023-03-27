package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoVia {
    @NotNull
    private Integer tipoVia;
    @NotNull
    @Size(max = 50)
    private String descripcion;

    public TipoVia(Integer tipoVia, String descripcion) {
        this.tipoVia = tipoVia;
        this.descripcion = descripcion;
    }

    public Integer getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(Integer tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
