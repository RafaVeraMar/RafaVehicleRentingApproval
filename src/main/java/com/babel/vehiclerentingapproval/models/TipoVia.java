package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoVia {
    @NotNull
    @NotEmpty
    @NotBlank
    private Integer tipoVia;
    @NotNull
    @NotEmpty
    @NotBlank
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
