package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter    @Setter
    private int tipoViaId;
    /**
     * Parametro que almacena la descripcion del tipo de via, de tipo String con un tamaño maximo de 50 caracteres
     */
    @NotNull
    @Size(max = 50)
    @Getter @Setter
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


    @Override
    public String toString() {
        return "TipoVia{" +
                "tipoViaId=" + getTipoViaId() +
                ", descripcion='" + getDescripcion() + '\'' +
                '}';
    }
}
