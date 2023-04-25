package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que modela el tipo de via
 * @author javier.roldan@babelgroup.com
 * @author tomas.prados@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
public class TipoVia {
    /**
     * Parametro que alamcena el identificador del tipo de via, de tipo Integer
     */
    private int tipoViaId;
    /**
     * Parametro que almacena la descripcion del tipo de via, de tipo String con un tamaño maximo de 50 caracteres
     */
    @NotNull
    @Size(max = 50)
    private String descripcion;
    /**
     * Constructor del modelo de tipo de via vacio
     */
    public TipoVia() {
    }

    /**
     * Constructor del modelo de tipo de via, pasandole como parametros los atributos explicados anteriormente
     * @param tipoViaId
     * @param descripcion
     */
    public TipoVia(Integer tipoViaId, String descripcion) {
        this.tipoViaId = tipoViaId;
        this.descripcion = descripcion;
    }

    /**
     * Metodo público que se encarga de mostrar el valor del identificador del tipo de via, de tipo Integer
     * @return devuelve el identificador del tipo de via
     */
    public int getTipoViaId() {
        return tipoViaId;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al identificador del tipo de via
     * @param tipoViaId es el valor que se le va a asignar al parametro indicado del objeto TipoVia
     */
    public void setTipoViaId(int tipoViaId) {
        this.tipoViaId = tipoViaId;
    }

    /**
     * Metodo público que se encarga de mostrar el valor de la descripcion del tipo de via, de tipo String
     * @return devuelve la descripcion del tipo de via
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la descripcion del tipo de via
     * @param descripcion es el valor que se le va a asignar al parametro indicado del objeto TipoVia
     */

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoVia{" +
                "tipoViaId=" + getTipoViaId() +
                ", descripcion='" + getDescripcion() + '\'' +
                '}';
    }
}
