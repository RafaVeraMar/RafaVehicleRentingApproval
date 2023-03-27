package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Profesion {
    @NotNull
    private int profesionId;
    @Size(max = 50)
    @NotNull
    private String descripcion;

    public int getProfesionId() {
        return profesionId;
    }

    public void setProfesionId(int profesionId) {
        this.profesionId = profesionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
