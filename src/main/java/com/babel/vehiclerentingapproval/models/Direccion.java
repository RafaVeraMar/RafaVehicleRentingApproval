package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author javier.roldan@babelgroup.com
 * @author andres.guijarro@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
public class Direccion {
    /**
     * Identificador de la direccion
     */
    @NotNull
    private int direccionId;
    /**
     * Identificador del tipo de via
     */
    @NotNull
    private TipoVia tipoViaId;
    /**
     * Parametro para almacenar el nombre de la calle
     */
    @NotNull
    @Size(max=50)
    private String nombreCalle;
    /**
     * Parametro para almacenar el numero de la calle
     */
    @NotNull
    @Size(max=10)
    private String numero;
    /**
     * Parametro para almacenar el piso del domicilio
     */
    @Size(max=10)
    private String piso;
    /**
     * Parametro para almacenar la puerta del domicilio
     */
    @Size(max=10)
    private String puerta;
    /**
     * Parametro para almacenar la escalera del domicilio
     */
    @Size(max=10)
    private String escalera;
    /**
     * Parametro para almacenar otros datos del domicilio
     */
    @Size(max=100)
    private String otroDato;
    /**
     * Parametro para almacenar el codigo postal del domicilio
     */
    @NotNull
    @Size(max=5)
    private String codPostal;
    /**
     * Parametro para almacenar el municipio del domicilio
     */
    @NotNull
    @Size(max=50)
    private String municipio;
    /**
     * Parametro para almacenar la provincia del domicilio
     */
    @NotNull
    @Size(max=2)
    private Provincia provincia;

    /**
     * Metodo público que se encarga de recuperar el valor de la id de la direccion
     * @return devuelve el id de la direccion
     */
    public int getDireccionId() {
        return direccionId;
    }
    /**
     * Metodo público que se encarga de establecer la ID de la direccion del domicilio
     * @param direccionId id de la direccion
     */
    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }
    /**
     * Metodo público que se encarga de recuperar el valor de la id del tipo de via
     * @return devuelve el id del tipo de via
     */
    public TipoVia getTipoViaId() {
        return tipoViaId;
    }
    /**
     * Metodo público que se encarga de establecer el tipo de via del domicilio
     * @param tipoViaId objeto TipoVia
     */
    public void setTipoViaId(TipoVia tipoViaId) {
        this.tipoViaId = tipoViaId;
    }
    /**
     * Metodo público que se encarga de recuperar el valor del nombre de la calle
     * @return devuelve el nombre de la calle
     */
    public String getNombreCalle() {
        return nombreCalle;
    }
    /**
     * Metodo público que se encarga de establecer el nombre de la calle del domicilio
     * @param nombreCalle nombre de la calle
     */
    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }
    /**
     * Metodo público que se encarga de recuperar el numero de la calle
     * @return devuelve el numero de la calle
     */
    public String getNumero() {
        return numero;
    }
    /**
     * Metodo público que se encarga de establecer el numero del domicilio
     * @param numero numero del domicilio
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * Metodo público que se encarga de recuperar el piso del domicilio
     * @return devuelve el piso del domicilio
     */
    public String getPiso() {
        return piso;
    }
    /**
     * Metodo público que se encarga de establecer el piso del domicilio
     * @param piso piso del domicilio
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }
    /**
     * Metodo público que se encarga de establecer el piso del domicilio
     * @return devuelve el piso del domicilio
     */
    public String getPuerta() {
        return puerta;
    }
    /**
     * Metodo público que se encarga de establecer la puerta del domicilio
     * @param puerta puerta del domicilio
     */
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }
    /**
     * Metodo público que se encarga de recuperar la escalera del domicilio
     * @return devuelve la escalera del domicilio
     */
    public String getEscalera() {
        return escalera;
    }
    /**
     * Metodo público que se encarga de establecer la escalera del domicilio
     * @param escalera escalera del domicilio
     */
    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }
    /**
     * Metodo público que se encarga de recuperar otros datos del domicilio
     * @return devuelve otros datos del domicilio
     */
    public String getOtroDato() {
        return otroDato;
    }
    /**
     * Metodo público que se encarga de establecer otro dato del domicilio
     * @param otroDato otros datos del domicilio
     */
    public void setOtroDato(String otroDato) {
        this.otroDato = otroDato;
    }
    /**
     * Metodo público que se encarga de recuperar el codigo postal del domicilio
     * @return devuelve el codigo postal del domicilio
     */
    public String getCodPostal() {
        return codPostal;
    }
    /**
     * Metodo público que se encarga de establecer el codigo postal del domicilio
     * @param codPostal codigo postal del domicilio
     */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }
    /**
     * Metodo público que se encarga de recuperar el municipio del domicilio
     * @return devuelve el municipio del domicilio
     */
    public String getMunicipio() {
        return municipio;
    }
    /**
     * Metodo público que se encarga de establecer el municipio del domicilio
     * @param municipio el municipio del domicilio
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    /**
     * Metodo público que se encarga de recuperar la provincia del domicilio
     * @return devuelve la provincia del domicilio
     */
    public Provincia getProvincia() {
        return provincia;
    }
    /**
     * Metodo público que se encarga de establecer la provincia del domicilio
     * @param provincia objeto provincia
     */
    public void setProvinciaCod(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "direccionId=" + this.getDireccionId() +
                ", tipoViaId=" + this.getTipoViaId().getTipoViaId() +'\'' +
                ", descripcionTipoVia=" + this.getTipoViaId().getDescripcion() +'\'' +
                ", nombreCalle='" + this.getNombreCalle() + '\'' +
                ", numero='" + this.getNumero() + '\'' +
                ", piso='" + this.getPiso() + '\'' +
                ", puerta='" + this.getPuerta() + '\'' +
                ", escalera='" + this.getEscalera() + '\'' +
                ", otroDato='" + this.getOtroDato() + '\'' +
                ", codPostal='" + this.getCodPostal() + '\'' +
                ", municipio='" + this.getMunicipio() + '\'' +
                ", codProvincia=" + this.getProvincia().getCodProvincia() +'\'' +
                ", codProvincia=" + this.getProvincia().getNombre() +'\'' +
                '}';
    }
}
