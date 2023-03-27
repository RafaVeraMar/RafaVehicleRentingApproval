package com.babel.vehiclerentingapproval.models;

public class Direccion {
    private int direccionId;
    private TipoVia tipoViaId;
    private String nombreCalle;
    private String numero;
    private String piso;
    private String puerta;
    private String escalera;
    private String otroDato;
    private String codPostal;
    private String municipio;
    private Provincia provinciaCod;

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public TipoVia getTipoViaId() {
        return tipoViaId;
    }

    public void setTipoViaId(TipoVia tipoViaId) {
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

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    public String getOtroDato() {
        return otroDato;
    }

    public void setOtroDato(String otroDato) {
        this.otroDato = otroDato;
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

    public Provincia getProvinciaCod() {
        return provinciaCod;
    }

    public void setProvinciaCod(Provincia provinciaCod) {
        this.provinciaCod = provinciaCod;
    }
}
