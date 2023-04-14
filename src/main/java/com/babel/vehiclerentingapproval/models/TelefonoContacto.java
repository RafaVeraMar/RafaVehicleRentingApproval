package com.babel.vehiclerentingapproval.models;

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
    private int telefonoId;
    /**
     * Parametro que almacena el telefono, de tipo String con un tamaño maximo de 50 caracteres
     */
    @NotNull
    @Size(max = 50)
    private String telefono;

    /**
     * Constructor del modelo de telefono de contacto, pasandole como parametro los atributos explicados anteriormente
     * @param telefonoId
     * @param telefono
     */
    public TelefonoContacto(int telefonoId, String telefono) {
        this.telefonoId = telefonoId;
        this.telefono = telefono;
    }

    /**
     * Metodo público que se encarga de mostrar el valor del identificador del telefono, de tipo int
     * @return devuelve el identificador del telefono
     */
    public int getTelefonoId() {
        return telefonoId;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al identificador del telefono
     * @param telefonoId es el valor que se le va a asignar al parametro indicado del objeto TelefonoContacto
     */
    public void setTelefonoId(int telefonoId) {
        this.telefonoId = telefonoId;
    }

    /**
     * Metodo público que se encarga de mostrar el numero de telefono, de tipo String
     * @return devuelve el telefono asociado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al telefono
     * @param telefono es el valor que se le va a asignar al parametro indicado del objeto TelefonoContacto
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
