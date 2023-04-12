package com.babel.vehiclerentingapproval.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

public class SolicitudRenting {
    private int solicitudId;
    private Persona persona;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaSolicitud;
    private BigInteger numVehiculos;
    private Float inversion;
    private Float cuota;
    private BigInteger plazo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaInicioVigor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaResolucion;
    private TipoResultadoSolicitud tipoResultadoSolicitud;

    public int getSolicitudId ( ) {
        return solicitudId;
    }

    public void setSolicitudId (int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Persona getPersona ( ) {
        return persona;
    }

    public void setPersona (Persona persona) {
        this.persona = persona;
    }

    public Date getFechaSolicitud ( ) {
        return fechaSolicitud;
    }

    public void setFechaSolicitud (Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public BigInteger getNumVehiculos ( ) {
        return numVehiculos;
    }

    public void setNumVehiculos (BigInteger numVehiculos) {
        this.numVehiculos = numVehiculos;
    }

    public Float getInversion ( ) {
        return inversion;
    }

    public void setInversion (Float inversion) {
        this.inversion = inversion;
    }

    public Float getCuota ( ) {
        return cuota;
    }

    public void setCuota (Float cuota) {
        this.cuota = cuota;
    }

    public BigInteger getPlazo ( ) {
        return plazo;
    }

    public void setPlazo (BigInteger plazo) {
        this.plazo = plazo;
    }

    public Date getFechaInicioVigor ( ) {
        return fechaInicioVigor;
    }

    public void setFechaInicioVigor (Date fechaInicioVigor) {
        this.fechaInicioVigor = fechaInicioVigor;
    }

    public Date getFechaResolucion ( ) {
        return fechaResolucion;
    }

    public void setFechaResolucion (Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public TipoResultadoSolicitud getTipoResultadoSolicitud ( ) {
        return tipoResultadoSolicitud;
    }

    public void setTipoResultadoSolicitud (TipoResultadoSolicitud tipoResultadoSolicitud) {
        this.tipoResultadoSolicitud = tipoResultadoSolicitud;
    }
}
