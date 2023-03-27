package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Direccion {
    @NotNull
    private int direccionId;
    @NotNull
    private TipoVia tipoViaId;
    @NotNull
    @Size(max=50)
    private String nombreCalle;
    @NotNull
    @Size(max=10)
    private String numero;
    @Size(max=10)
    private String piso;
    @Size(max=10)
    private String puerta;
    @Size(max=10)
    private String escalera;
    @Size(max=100)
    private String otroDato;
    @NotNull
    @Size(max=5)
    private String codPostal;
    @NotNull
    @Size(max=50)
    private String municipio;
    @NotNull
    @Size(max=2)
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
