package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Profesion {
    @NotNull
    @NotEmpty
    @NotBlank
    private int profesionId;
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String descripcion;

    public Profesion(int profesionId, String descripcion) {
        this.profesionId = profesionId;
        this.descripcion = descripcion;
    }

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
