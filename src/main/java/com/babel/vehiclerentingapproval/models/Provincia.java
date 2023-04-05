package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.*;

public class Provincia {

    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    @Size(max=2)
    private String codProvincia;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=50)

    private String nombre;

    public Provincia(String codProvincia, String nombre) {
        this.codProvincia = codProvincia;
        this.nombre = nombre;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }
}
