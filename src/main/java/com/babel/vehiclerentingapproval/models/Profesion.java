package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que define el modelo de Profesion
 * @author andres.guijarro@babelgroup.com
 */
public class Profesion {
    /**
     * Identificador de la profesion de tipo int
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private int profesionId;
    /**
     * Parametro que almacena la descripcion de la profesion, de tipo String
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private String descripcion;

    /**
     * Constructor del objeto Profesion vacio
     */
    public Profesion() {
    }

    /**
     * Constructor del objeto Profesion, con los parametros explicados anteriormente
     * @param profesionId
     * @param descripcion
     */
    public Profesion(int profesionId, String descripcion) {
        this.profesionId = profesionId;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Profesion{" +
                "profesionId=" + getProfesionId() +
                ", descripcion='" + getDescripcion() + '\'' +
                '}';
    }
}
