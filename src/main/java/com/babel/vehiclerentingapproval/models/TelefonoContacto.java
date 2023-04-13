package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TelefonoContacto {

    @NotNull
    private int telefonoId;
    @NotNull
    @Size(max = 50)
    private String telefono;

    public TelefonoContacto(int telefonoId, String telefono) {
        this.telefonoId = telefonoId;
        this.telefono = telefono;
    }

    public int getTelefonoId() {
        return telefonoId;
    }

    public void setTelefonoId(int telefonoId) {
        this.telefonoId = telefonoId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
