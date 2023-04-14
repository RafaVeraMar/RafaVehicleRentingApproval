package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que modela el objeto de la resolucion de la solicitud
 * @author andres.guijarro@babelgroup.com
 */
public class ResolucionSolicitud {
    /**
     * Parametro que indica el codigo de resultado de la resolucion, que como maximo tiene un tamaño de 2, de tipo String
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=2)
    private String codigoResultado;
    /**
     * Parametro que indica la descripcion de la resolucion, de tipo String
     */
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max=100)
    private String descripcion;

    /**
     * Constructor del modelo de resolucion de la solicitud, pasandole como parametro los atributos anteriormente explicados
     * @param codigoResultado
     * @param descripcion
     */
    public ResolucionSolicitud(String codigoResultado, String descripcion) {
        this.codigoResultado = codigoResultado;
        this.descripcion = descripcion;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del codigo de resultado, de tipo String
     * @return devuelve el codigo de resultado asociado a la resolucion de la solicitud
     */
    public String getCodigoResultado() {
        return codigoResultado;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al codigo resultado de la resolucion
     * @param codigoResultado es el valor que se le va a asignar al parametro indicado del objeto ResolucionSolicitud
     */
    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }
    /**
     * Metodo público que se encarga de mostrar el valor de la descripcion, de tipo String
     * @return devuelve la descripcion asociada a la resolucion de la solicitud
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la descripcion de la resolucion
     * @param descripcion es el valor que se le va a asignar al parametro indicado del objeto ResolucionSolicitud
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
