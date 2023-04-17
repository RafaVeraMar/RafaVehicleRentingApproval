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
    private String isoAlfa_2;
    /**
     * Identificador del pais de tipo Integer
     */
    @NotNull
    @Size(max=38)
    private Integer isoNum_3;
    /**
     * Identificador del pais de tipo String, formado por tres caracteres
     */
    @NotNull
    @Size(max=3)
    private String isoAlfa_3;
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
     * @param isoAlfa_2
     * @param isoNum_3
     * @param isoAlfa_3
     * @param nombre
     * @param orden
     */


    public Pais(String isoAlfa_2, Integer isoNum_3, String isoAlfa_3, String nombre, Integer orden) {
        this.isoAlfa_2 = isoAlfa_2;
        this.isoNum_3 = isoNum_3;
        this.isoAlfa_3 = isoAlfa_3;
        this.nombre = nombre;
        this.orden = orden;
    }

    /**
     * Metodo público que se encarga de mostrar el valor del identificador de dos caracteres del pais
     * @return devuelve el identificador asociado al pais
     */
    public String getIsoAlfa_2() {
        return isoAlfa_2;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al identificador de dos caracteres del pais
     * @param isoAlfa_2 es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setIsoAlfa_2(String isoAlfa_2) {
        this.isoAlfa_2 = isoAlfa_2;
    }

    /**
     * Metodo público que se encarga de mostrar el valor del identificador numerico del pais
     * @return devuelve el identificador asociado al pais
     */
    public Integer getIsoNum_3() {
        return isoNum_3;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al identificador numerico del pais
     * @param isoNum_3 es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setIsoNum_3(Integer isoNum_3) {
        this.isoNum_3 = isoNum_3;
    }

    /**
     * Metodo publico que se encarga de mostrar el valor al identificador de tres caracteres del pais
     * @return devuelve el identificador asociado al pais
     */
    public String getIsoAlfa_3() {
        return isoAlfa_3;
    }

    /**
     * Metodo publico que se encarga de darle el valor pasado como parametro al identificador de tres caracteres del pais
     * @param isoAlfa_3 es el valor que se le va a asignar al parametro indicado del objeto pais
     */
    public void setIsoAlfa_3(String isoAlfa_3) {
        this.isoAlfa_3 = isoAlfa_3;
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
                "isoAlfa_2='" + this.getIsoAlfa_2() + '\'' +
                ", isoNum_3=" + this.getIsoNum_3() +
                ", isoAlfa_3='" + this.getIsoAlfa_3() + '\'' +
                ", nombre='" + this.getNombre() + '\'' +
                ", orden=" + this.getOrden() +
                '}';
    }
}
