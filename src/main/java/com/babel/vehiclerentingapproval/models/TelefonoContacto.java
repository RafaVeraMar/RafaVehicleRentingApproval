package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TelefonoContacto {

    @NotNull
    @NotEmpty
    @NotBlank
    private String telefonoID;
    @NotNull
    @NotEmpty
    @NotBlank
    private Integer personaID;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 50)
    private String telefono;

    public TelefonoContacto(String telefonoID, Integer personaID, String telefono) {
        this.telefonoID = telefonoID;
        this.personaID = personaID;
        this.telefono = telefono;
    }

    public Integer getPersona() {
        return personaID;
    }

    public void setPersona(Integer persona) {
        this.personaID = persona;
    }

    public String getTelefonoID() {
        return telefonoID;
    }

    public void setTelefonoID(String telefonoID) {
        this.telefonoID = telefonoID;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
