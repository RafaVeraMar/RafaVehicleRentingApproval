package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter    @Setter
    private String codProvincia;
    /**
     * Parametro que indica el nombre de la provincia, de tipo String
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=50)
    @Getter    @Setter
    private String nombre;
    /**
     * Constructor del objeto Provincia vacio
     */
    public Provincia() {
    }

    /**
     * Constructor del objeto Provincia, pasandole como parametro los atributos indicados anteriomente
     * @param codProvincia
     * @param nombre
     */
    public Provincia(String codProvincia, String nombre) {
        this.codProvincia = codProvincia;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "codProvincia='" + getCodProvincia() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                '}';
    }
}
