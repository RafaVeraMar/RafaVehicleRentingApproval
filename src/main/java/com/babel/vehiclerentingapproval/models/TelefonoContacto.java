package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefonoContacto that = (TelefonoContacto) o;
        return telefonoId == that.telefonoId && telefono.equals(that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefonoId, telefono);
    }
}
