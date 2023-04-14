package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.*;

/**
 * @author javier.roldan@babelgroup.com
 */
public class Provincia {
    /**
     * Parametro que indica el codigo de la provincia de tipo String, que en este caso es la clave primaria
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    @Size(max=2)
    private String codProvincia;
    /**
     * Parametro que indica el nombre de la provincia, de tipo String
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=50)
    private String nombre;

    /**
     * Constructor del objeto Provincia, pasandole como parametro los atributos indicados anteriomente
     * @param codProvincia
     * @param nombre
     */
    public Provincia(String codProvincia, String nombre) {
        this.codProvincia = codProvincia;
        this.nombre = nombre;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del codigo de la provincia de tipo String
     * @return devuelve el codigo de la provincia asociado a la profesion
     */
    public String getCodProvincia() {
        return codProvincia;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al codigo de la provincia
     * @param codProvincia es el valor que se le va a asignar al parametro indicado del objeto provincia
     */
    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    /**
     * Metodo público que se encarga de mostrar el valor del nombre de la provincia
     * @return devuelve el nombre asociado a la provincia
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al nombre de la provincia
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
