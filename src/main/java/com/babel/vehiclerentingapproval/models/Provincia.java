package com.babel.vehiclerentingapproval.models;

public class Provincia {
    private String codProvincia;
    private String nombre;

    public Provincia(String codProvincia, String nombre) {
        this.codProvincia = codProvincia;
        this.nombre = nombre;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }
}
