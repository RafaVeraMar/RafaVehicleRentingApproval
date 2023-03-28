package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoVia {
    @NotNull
    private Integer tipoViaId;
    @NotNull
    @Size(max = 50)
    private String descripcion;


    public TipoVia(Integer tipoViaId, String descripcion) {
        this.tipoViaId = tipoViaId;
        this.descripcion = descripcion;
    }

    public Integer getTipoViaId() {
        return tipoViaId;
    }

    public void setTipoViaId(Integer tipoViaId) {
        this.tipoViaId = tipoViaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
