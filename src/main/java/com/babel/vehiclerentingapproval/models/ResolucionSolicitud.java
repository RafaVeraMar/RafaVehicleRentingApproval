package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResolucionSolicitud {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=2)
    private String codigoResultado;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=100)
    private String descripcion;

    public ResolucionSolicitud(String codigoResultado, String descripcion) {
        this.codigoResultado = codigoResultado;
        this.descripcion = descripcion;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
