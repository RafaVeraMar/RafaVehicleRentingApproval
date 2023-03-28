package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TelefonoContacto {

    @NotNull
    private String telefonoId;
    @NotNull
    private Persona personaId;
    @NotNull
    @Size(max = 50)
    private String telefono;

    public TelefonoContacto(String telefonoId, Persona personaId, String telefono) {
        this.telefonoId = telefonoId;
        this.personaId = personaId;
        this.telefono = telefono;
    }

    public String getTelefonoId() {
        return telefonoId;
    }

    public void setTelefonoId(String telefonoId) {
        this.telefonoId = telefonoId;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
