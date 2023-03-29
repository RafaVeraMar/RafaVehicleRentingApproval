package com.babel.vehiclerentingapproval.models;

import java.util.Date;

public class SolicitudRenting {

    private int solicitudId;
    private Persona persona;
    private Date fechaSolicitud;
    private int numVehiculos;
    private float inversion;
    private float cuota;
    private Integer plazo;
    private Date fechaInicioVigor;

    public SolicitudRenting(int solicitudId, Persona persona, Date fechaSolicitud, int numVehiculos, float inversion, float cuota, Integer plazo, Date fechaInicioVigor, Date fechaResolucion, TipoResultadoSolicitud tipoResultadoSolicitud) {
        this.solicitudId = solicitudId;
        this.persona = persona;
        this.fechaSolicitud = fechaSolicitud;
        this.numVehiculos = numVehiculos;
        this.inversion = inversion;
        this.cuota = cuota;
        this.plazo = plazo;
        this.fechaInicioVigor = fechaInicioVigor;
        this.fechaResolucion = fechaResolucion;
        this.tipoResultadoSolicitud = tipoResultadoSolicitud;
    }

    private Date fechaResolucion;
    private TipoResultadoSolicitud tipoResultadoSolicitud;

    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getNumVehiculos() {
        return numVehiculos;
    }

    public void setNumVehiculos(int numVehiculos) {
        this.numVehiculos = numVehiculos;
    }

    public float getInversion() {
        return inversion;
    }

    public void setInversion(float inversion) {
        this.inversion = inversion;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public Date getFechaInicioVigor() {
        return fechaInicioVigor;
    }

    public void setFechaInicioVigor(Date fechaInicioVigor) {
        this.fechaInicioVigor = fechaInicioVigor;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public TipoResultadoSolicitud getTipoResultadoSolicitud() {
        return tipoResultadoSolicitud;
    }

    public void setTipoResultadoSolicitud(TipoResultadoSolicitud tipoResultadoSolicitud) {
        this.tipoResultadoSolicitud = tipoResultadoSolicitud;
    }
}
