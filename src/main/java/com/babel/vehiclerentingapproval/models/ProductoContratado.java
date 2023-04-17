package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.*;
import java.util.Date;
/**
 * Clase que define el modelo de Productos Contratados.
 * @author tomas.prados@babelgroup.com
 */
public class ProductoContratado {
    /**
     * Identificador del Producto Contratado
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    private int idProductoContratado;
    /**
     * Identificador del Producto
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    private int idProducto;
    /**
     * Parametro para asignar un alias al Producto
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String alias;
    /**
     * Parametro que asigna un importe nominal al Producto
     */
    @NotNull
    @NotEmpty
    @NotBlank
    private int importeNominal;
    /**
     * Parametro que almacena la fecha de alta del Producto
     */
    @PastOrPresent
    private Date fechaAlta;
    /**
     * Parametro que almacena la fecha de baja del Producto
     */
    private Date fechaBaja;
    /**
     * Parametro que almacena estado, Vigente o Vencido, del Producto
     */
    private EstadoProductoContratado estado;

    /**
     * Metodo constructor de la clase con todos los atributos no nulos como parametros
     * @param idProductoContratado id del producto contratado
     * @param idProducto id del producto
     * @param alias alias del producto
     * @param importeNominal importe nominal del producto
     * @param fechaAlta fecha de alta del producto
     */
    public ProductoContratado(int idProductoContratado, int idProducto, String alias, int importeNominal, Date fechaAlta) {
        this.idProductoContratado = idProductoContratado;
        this.idProducto = idProducto;
        this.alias = alias;
        this.importeNominal = importeNominal;
        this.fechaAlta = fechaAlta;
    }

    /**
     * Metodo publico que se encarga de recuperar la id del producto contratado
     * @return devuelve la id del producto contratado
     */
    public int getIdProductoContratado() {
        return idProductoContratado;
    }

    /**
     * Metodo publico que se encarga de establecer la id del producto contratado
     * @param idProductoContratado id del producto contratado
     */
    public void setIdProductoContratado(int idProductoContratado) {
        this.idProductoContratado = idProductoContratado;
    }

    /**
     * Metodo publico que se encarga de recuperar el valor de la id del producto
     * @return devuelve la id del producto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Metodo publico que se encarga de establecer el valor de la id del producto
     * @param idProducto id del producto
     */

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Metodo publico que se encarga de recuperar el valor del alias
     * @return devuelve el alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Metodo publico que se encarga de establecer el valor del alias
     * @param alias alias del producto
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Metodo publico que se encarga de recuperar el importe nominal del producto
     * @return devuelve el importe nominal
     */
    public int getImporteNominal() {
        return importeNominal;
    }

    /**
     * Metodo publico que se encarga de establecer el importe nominal del producto
     * @param importeNominal importe nominal del producto
     */
    public void setImporteNominal(int importeNominal) {
        this.importeNominal = importeNominal;
    }

    /**
     * Metodo publico que se encarga de recuperar la fecha de alta del producto
     * @return devuelve la fecha de alta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Metodo publico que se encarga de establecer la fecha de alta del producto
     * @param fechaAlta objeto fecha
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Metodo publico que se encarga de recuperar la fecha de baja del producto
     * @return devuelve la fecha de baja
     */
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Metodo publico que se encarga de establecer la fecha de baja del producto
     * @param fechaBaja objeto fecha
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Metodo publico que se encarga de recuperar el estado del producto contratado
     * @return devuelve el estado del producto contratado
     */
    public EstadoProductoContratado getEstado() {
        return estado;
    }

    /**
     * Metodo publico que se encarga de establecer el estado del producto contratado
     * @param estado objeto EstadoProductoContratado
     */
    public void setEstado(EstadoProductoContratado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ProductoContratado{" +
                "idProductoContratado=" + idProductoContratado +
                ", idProducto=" + idProducto +
                ", alias='" + alias + '\'' +
                ", importeNominal=" + importeNominal +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", estado=" + estado +
                '}';
    }
}
