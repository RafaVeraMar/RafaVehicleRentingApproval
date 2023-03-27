package com.babel.vehiclerentingapproval.models;
import java.util.Date;
import java.util.List;

public class Persona {
    private int personaId;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Direccion direccionDomicilio;
    private Direccion direccionNotificacion;
    private boolean direccionDomicilioSameAsNotificacion = true;
    private String nif;
    private Date fechaNacimiento;
    private Pais nacionalidad;
    private int scoring;
    private Date fechaScoring;
    private List<TelefonoContacto> telefonos;

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Direccion getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(Direccion direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public Direccion getDireccionNotificacion() {
        return direccionNotificacion;
    }

    public void setDireccionNotificacion(Direccion direccionNotificacion) {
        this.direccionNotificacion = direccionNotificacion;
    }

    public boolean isDireccionDomicilioSameAsNotificacion() {
        return direccionDomicilioSameAsNotificacion;
    }

    public void setDireccionDomicilioSameAsNotificacion(boolean direccionDomicilioSameAsNotificacion) {
        this.direccionDomicilioSameAsNotificacion = direccionDomicilioSameAsNotificacion;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getScoring() {
        return scoring;
    }

    public void setScoring(int scoring) {
        this.scoring = scoring;
    }

    public Date getFechaScoring() {
        return fechaScoring;
    }

    public void setFechaScoring(Date fechaScoring) {
        this.fechaScoring = fechaScoring;
    }

    public List<TelefonoContacto> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoContacto> telefonos) {
        this.telefonos = telefonos;
    }
}
    //getters y setters

