package com.babel.vehiclerentingapproval.models;

/**
 * Clase que modela el tipo de resultado de la solicitud
 * @author miguel.sdela@babelgroup.com
 * @author alvaro.aleman@babelgroup.com
 */
public class TipoResultadoSolicitud {
    /**
     * Parametro que almacena el codigo de resultado, de tipo String
     */
    private String codResultado;
    /**
     * Parametro que almacena la descripcion del tipo de resultado de la solicitud, de tipo String
     */
    private String descripcion;
    /**
     * Constructor vacío
     */
    public TipoResultadoSolicitud() {
        //Constructor intencionadamente vacío para el uso de todos los setters en los tests
    }

    /**
     * Metodo público que se encarga de mostrar el valor del codigo de resultado, de tipo String
     * @return devuelve el codigo de resultado
     */
    public String getCodResultado ( ) {
        return codResultado;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al codigo de resultado
     * @param codResultado es el valor que se le va a asignar al parametro indicado del objeto TipoResultadoSolicitud
     */
    public void setCodResultado (String codResultado) {
        this.codResultado = codResultado;
    }

    /**
     * Metodo público que se encarga de mostrar el valor de la descripcion, de tipo String
     * @return devuelve la descripcion del tipo de resultado de la solicitud
     */
    public String getDescripcion ( ) {
        return descripcion;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la descripcion del resultado de la solicitud
     * @param descripcion es el valor que se le va a asignar al parametro indicado del objeto TipoResultadoSolicitud
     */
    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoResultadoSolicitud{" +
                "codResultado='" + getCodResultado() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                '}';
    }
}
