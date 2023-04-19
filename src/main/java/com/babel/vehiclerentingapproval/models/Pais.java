package com.babel.vehiclerentingapproval.models;

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
    private String isoAlfa2;
    /**
     * Identificador del pais de tipo Integer
     */
    @NotNull
    @Size(max=38)
    private Integer isoNum3;
    /**
     * Identificador del pais de tipo String, formado por tres caracteres
     */
    @NotNull
    @Size(max=3)
    private String isoAlfa3;
    /**
     * Parametro que indica el nombre del pais
     */
    @NotNull
    @Size(max=100)
    private String nombre;
    /**
     * Parametro que indica el orden del pais
     */
    @Size(max=38)
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

    /**
     * Metodo público que se encarga de mostrar el valor del identificador de dos caracteres del pais
     * @return devuelve el identificador asociado al pais
     */
    public String getIsoAlfa2() {
        return isoAlfa2;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al identificador de dos caracteres del pais
     * @param isoAlfa2 es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setIsoAlfa2(String isoAlfa2) {
        this.isoAlfa2 = isoAlfa2;
    }

    /**
     * Metodo público que se encarga de mostrar el valor del identificador numerico del pais
     * @return devuelve el identificador asociado al pais
     */
    public Integer getIsoNum3() {
        return isoNum3;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al identificador numerico del pais
     * @param isoNum3 es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setIsoNum3(Integer isoNum3) {
        this.isoNum3 = isoNum3;
    }

    /**
     * Metodo publico que se encarga de mostrar el valor al identificador de tres caracteres del pais
     * @return devuelve el identificador asociado al pais
     */
    public String getIsoAlfa3() {
        return isoAlfa3;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al identificador de tres caracteres del pais
     * @param isoAlfa3 es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setIsoAlfa3(String isoAlfa3) {
        this.isoAlfa3 = isoAlfa3;
    }

    /**
     * Metodo publico que se encarga de mostrar el valor del nombre del pais
     * @return devuelve el nombre asociado a un pais
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al nombre del pais
     * @param nombre es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo publico que se encarga de mostrar el valor asociado al orden del pais
     * @return devuelve el orden asociado al pais
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al nombre del pais
     * @param orden es el valor que se le va a asignar al parametro indicado del objeto pais
     */

    public void setOrden(Integer orden) {
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
