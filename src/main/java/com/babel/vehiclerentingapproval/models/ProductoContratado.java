package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter    @Setter
    private int idProductoContratado;
    /**
     * Identificador del Producto
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    @Getter    @Setter
    private int idProducto;
    /**
     * Parametro para asignar un alias al Producto
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private String alias;
    /**
     * Parametro que asigna un importe nominal al Producto
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private int importeNominal;
    /**
     * Parametro que almacena la fecha de alta del Producto
     */
    @PastOrPresent
    @Getter    @Setter
    private Date fechaAlta;
    /**
     * Parametro que almacena la fecha de baja del Producto
     */
    @Getter    @Setter
    private Date fechaBaja;
    /**
     * Parametro que almacena estado, Vigente o Vencido, del Producto
     */
    @Getter    @Setter
    private EstadoProductoContratado estado;

    /**
     * Metodo constructor de la clase vacio
     */
    public ProductoContratado() {

    }

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


    @Override
    public String toString() {
        return "ProductoContratado{" +
                "idProductoContratado=" + getIdProductoContratado() +
                ", idProducto=" + getIdProducto() +
                ", alias='" + getAlias() + '\'' +
                ", importeNominal=" + getImporteNominal() +
                ", fechaAlta=" + getFechaAlta() +
                ", fechaBaja=" + getFechaBaja() +
                ", estado=" + getEstado() +
                '}';
    }
}
