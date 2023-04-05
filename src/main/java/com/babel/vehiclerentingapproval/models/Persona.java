package com.babel.vehiclerentingapproval.models;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class Persona {
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    private int personaId;
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String nombre;
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    private String apellido1;
    @Size(max = 50)
    private String apellido2;
    @NotNull
    @NotEmpty
    @NotBlank
    private Direccion direccionDomicilio;
    @NotNull
    @NotEmpty
    @NotBlank
    private Direccion direccionNotificacion;
    private boolean direccionDomicilioSameAsNotificacion = true;
    @Size(max=10)
    @NotNull
    @NotEmpty
    @NotBlank
    private String nif;
    @PastOrPresent
    private Date fechaNacimiento;
    @NotNull
    @NotEmpty
    @NotBlank
    private Pais nacionalidad;
    private int scoring;
    @PastOrPresent
    private Date fechaScoring;
    private List<TelefonoContacto> telefonos;
    private List<ProductoContratado> productosContratados;

    private String email;

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


    public List<ProductoContratado> getProductosContratados() {
        return productosContratados;
    }

    public void setProductosContratados(List<ProductoContratado> productosContratados) {
        this.productosContratados = productosContratados;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


