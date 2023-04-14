package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author andres.guijarro@babelgroup.com
 */
public class ResolucionSolicitud {
    /**
     * Codigo del resultado
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=2)
    private String codigoResultado;
    /**
     * Descripción
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=100)
    private String descripcion;

    public ResolucionSolicitud(String codigoResultado, String descripcion) {
        this.codigoResultado = codigoResultado;
        this.descripcion = descripcion;
    }
    /**
     * Metodo público que se encarga de recuperar el codigo de resultado
     * @return el codigo de resultado como String
     */
    public String getCodigoResultado() {
        return codigoResultado;
    }
    /**
     * Metodo público que se encarga de establecer el código de resultado
     * @param codigoResultado codigo de resultado
     */
    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }
    /**
     * Metodo público que se encarga de recuperar la descripcion
     * @return la descripcion como String
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Metodo público que se encarga de establecer la descripcion
     * @param descripcion descripcion de la resolucion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
