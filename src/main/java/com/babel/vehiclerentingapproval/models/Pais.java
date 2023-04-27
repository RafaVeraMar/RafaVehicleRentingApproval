package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Esta clase modela el objeto Pais
 * @author javier.roldan@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
public class Pais {
    /**
     * Identificador del pais de tipo String, formado por dos caracteres
     */
    @NotNull
    @Size(max=2)
    @Getter @Setter
    private String isoAlfa2;
    /**
     * Identificador del pais de tipo Integer
     */
    @NotNull
    @Size(max=38)
    @Getter @Setter
    private Integer isoNum3;
    /**
     * Identificador del pais de tipo String, formado por tres caracteres
     */
    @NotNull
    @Size(max=3)
    @Getter @Setter
    private String isoAlfa3;
    /**
     * Parametro que indica el nombre del pais
     */
    @NotNull
    @Size(max=100)
    @Getter @Setter
    private String nombre;
    /**
     * Parametro que indica el orden del pais
     */
    @Size(max=38)
    @Getter @Setter
    private Integer orden;

    /**
     * Constructor del pais vacío
     */
    public Pais(){}

    /**
     * Constructor del pais formado por todos los atributos del pais explicados anteriormente
     * @param isoAlfa2
     * @param isoNum3
     * @param isoAlfa3
     * @param nombre
     * @param orden
     */


    public Pais(String isoAlfa2, Integer isoNum3, String isoAlfa3, String nombre, Integer orden) {
        this.isoAlfa2 = isoAlfa2;
        this.isoNum3 = isoNum3;
        this.isoAlfa3 = isoAlfa3;
        this.nombre = nombre;
        this.orden = orden;
    }


    @Override
    public String toString() {
        return "Pais{" +
                "isoAlfa2='" + this.getIsoAlfa2() + '\'' +
                ", isoNum3=" + this.getIsoNum3() +
                ", isoAlfa3='" + this.getIsoAlfa3() + '\'' +
                ", nombre='" + this.getNombre() + '\'' +
                ", orden=" + this.getOrden() +
                '}';
    }
}
