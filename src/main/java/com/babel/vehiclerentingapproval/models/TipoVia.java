package com.babel.vehiclerentingapproval.models;

public class TipoVia {
    private Integer tipoVia;
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
