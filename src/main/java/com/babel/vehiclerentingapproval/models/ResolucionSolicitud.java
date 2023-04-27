package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Esta clase representa la resolucion de una solicitud de renting, que consta de un codigo con el tipo que representa
 * y una descripcion del propio codigo
 * @author andres.guijarro@babelgroup.com
 */
public class ResolucionSolicitud {
    /**
     * Este atributo corresponde al codigo del resultado de la solicitud
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=2)
    @Getter    @Setter
    private String codigoResultado;

    /**
     * Este atributo corresponde a la descripcion del codigo del resultado de la solicitud renting
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=100)
    @Getter    @Setter
    private String descripcion;
    /**
     * Este es el constructor de la clase {@link ResolucionSolicitud} vacia
     */

    public ResolucionSolicitud() {
    }

    /**
     * Este es el constructor de la clase {@link ResolucionSolicitud} que utiliza como parametros un codigoResultado y una descripcion
     * @param codigoResultado
     * @param descripcion
     */
    public ResolucionSolicitud(String codigoResultado, String descripcion) {
        this.codigoResultado = codigoResultado;
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "ResolucionSolicitud{" +
                "codigoResultado='" + getCodigoResultado() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                '}';
    }
}
