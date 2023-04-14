package com.babel.vehiclerentingapproval.models;

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
    private int profesionId;
    /**
     * Parametro que almacena la descripcion de la profesion, de tipo String
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String descripcion;

    /**
     * Constructor del objeto Profesion, con los parametros explicados anteriormente
     * @param profesionId
     * @param descripcion
     */
    public Profesion(int profesionId, String descripcion) {
        this.profesionId = profesionId;
        this.descripcion = descripcion;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del identificador de tipo int de la profesion
     * @return devuelve el identificador asociado a la profesion
     */
    public int getProfesionId() {
        return profesionId;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al identificador de la profesion
     * @param profesionId es el valor que se le va a asignar al parametro indicado del objeto profesion
     */
    public void setProfesionId(int profesionId) {
        this.profesionId = profesionId;
    }
    /**
     * Metodo público que se encarga de mostrar el valor de la descripcion de tipo String de la profesion
     * @return devuelve la decripcion asociada a la profesion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la descripcion de la profesion
     * @param descripcion es el valor que se le va a asignar al parametro indicado del objeto profesion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
