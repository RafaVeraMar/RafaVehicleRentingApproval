package com.babel.vehiclerentingapproval.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SolicitudRenting {
    private int solicitudId;
    private Persona persona;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaSolicitud;
    private Long numVehiculos;
    private Float inversion;
    private Float cuota;
    private Long plazo;
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

    public Long getNumVehiculos ( ) {
        return numVehiculos;
    }

    public void setNumVehiculos (long numVehiculos) {
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

    public Long getPlazo ( ) {
        return plazo;
    }

    public void setPlazo (Long plazo) {
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
