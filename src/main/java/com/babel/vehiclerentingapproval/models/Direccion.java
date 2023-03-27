package com.babel.vehiclerentingapproval.models;

public class Direccion {
    private int direccionId;
    private int tipoViaId;
    private String nombreCalle;
    private String numero;
    private String piso;
    private String codPostal;
    private String municipio;
    private String provinciaCod;

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public int getTipoViaId() {
        return tipoViaId;
    }

    public void setTipoViaId(int tipoViaId) {
        this.tipoViaId = tipoViaId;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvinciaCod() {
        return provinciaCod;
    }

    public void setProvinciaCod(String provinciaCod) {
        this.provinciaCod = provinciaCod;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }
}
