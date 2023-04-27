package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que modela el telefono de contacto
 * @author javier.roldan@babelgroup.com
 * @author tomas.prados@babelgroup.com
 */
public class TelefonoContacto {
    /**
     * Parametro que almacena el identificador del telefono de contacto, de tipo int
     */
    @NotNull
    @Getter    @Setter
    private int telefonoId;
    /**
     * Parametro que almacena el telefono, de tipo String con un tamaño maximo de 50 caracteres
     */
    @NotNull
    @Size(max = 50)
    @Getter    @Setter
    private String telefono;
    /**
     * Constructor del modelo de telefono de contacto vacio

     */
    public TelefonoContacto() {
    }

    /**
     * Constructor del modelo de telefono de contacto, pasandole como parametro los atributos explicados anteriormente
     * @param telefonoId
     * @param telefono
     */
    public TelefonoContacto(int telefonoId, String telefono) {
        this.telefonoId = telefonoId;
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "TelefonoContacto{" +
                "telefonoId=" + getTelefonoId() +
                ", telefono='" + getTelefono() + '\'' +
                '}';
    }
}
