package com.babel.vehiclerentingapproval.models;

public class TelefonoContacto {

    private String telefonoID;
    private Integer personaID;
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
