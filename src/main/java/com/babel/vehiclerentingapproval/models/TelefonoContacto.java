package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TelefonoContacto {

    @NotNull
    private String telefonoID;
    @NotNull
    private Integer personaID;
    @NotNull
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
