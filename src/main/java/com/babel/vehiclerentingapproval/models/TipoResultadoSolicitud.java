package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que modela el tipo de resultado de la solicitud
 * @author miguel.sdela@babelgroup.com
 * @author alvaro.aleman@babelgroup.com
 */
public class TipoResultadoSolicitud {
    /**
     * Parametro que almacena el codigo de resultado, de tipo String
     */
    @Getter @Setter
    private String codResultado;
    /**
     * Parametro que almacena la descripcion del tipo de resultado de la solicitud, de tipo String
     */
    @Getter @Setter
    private String descripcion;
    /**
     * Constructor vacío
     */
    public TipoResultadoSolicitud() {
        //Constructor intencionadamente vacío para el uso de todos los setters en los tests
    }


    @Override
    public String toString() {
        return "TipoResultadoSolicitud{" +
                "codResultado='" + getCodResultado() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                '}';
    }
}
