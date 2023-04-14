package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Este clase modela el tipo de via
 *
 * @author tomas.prados@babelgroup.com
 * @author javier.roldan@babelgroup.com
 * @author enrique.munoz@babelgroup.com

 */
public class TipoVia {
    /**
     * El identificador del tipo de via
     */
    @NotNull
    private Integer tipoViaId;
    /**
     * La descripcion que define el tipo de via
     */
    @NotNull
    @Size(max = 50)
    private String descripcion;

    /**
     * Constructor de la clase {TipoVia}
     * @param tipoViaId
     * @param descripcion
     */

    public TipoVia(Integer tipoViaId, String descripcion) {
        this.tipoViaId = tipoViaId;
        this.descripcion = descripcion;
    }

    /**
     * Metodo publico que devuelve el identificador del tipo de via
     * @return devuelve el identificador delk tipo de via
     */
    public Integer getTipoViaId() {
        return tipoViaId;
    }

    /**
     * metodo publico que inicializa el tipo de via
     * @param tipoViaId
     */
    public void setTipoViaId(Integer tipoViaId) {
        this.tipoViaId = tipoViaId;
    }

    /**
     * metodo publico que devuelve la descripcion del tipo de via
     * @return
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * metodo publico que inicializa la descripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
