package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que modela el telefono de contacto
 *
 * @author tomas.prados@babelgroup.com
 * @author javier.roldan@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
public class TelefonoContacto {
    /**
     * Atributo que representa el identificador del telefono
     */
    @NotNull
    private int telefonoId;
    /**
     * Atributo que constituye el telefono
     */
    @NotNull
    @Size(max = 50)
    private String telefono;

    /**
     * Constructor de la clase TelefonoContacto que utiliza el identificador del telefono y el telefono
     * @param telefonoId identificador del telefono
     * @param telefono telefono de contacto
     */
    public TelefonoContacto(int telefonoId, String telefono) {
        this.telefonoId = telefonoId;
        this.telefono = telefono;
    }

    /**
     * Metodo publico que devuelve el identificador del telefono
     * @return telefonoId
     */
    public int getTelefonoId() {
        return telefonoId;
    }

    /**
     * Metodo publico que inicializa el identificador del telefono
     * @param telefonoId
     */
    public void setTelefonoId(int telefonoId) {
        this.telefonoId = telefonoId;
    }
    /**
     * Metodo publico que devuelve el telefono
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Metodo publico que inicializa el telefono
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
