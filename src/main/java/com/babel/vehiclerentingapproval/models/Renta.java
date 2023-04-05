package com.babel.vehiclerentingapproval.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class Renta {
    @NotNull @NotEmpty @NotBlank @Positive
    private int rentaId;
    @NotNull @NotEmpty @NotBlank
    private Persona persona;
    @NotNull @NotEmpty @NotBlank
    private Profesion profesion;
    @NotNull @NotEmpty @NotBlank @Positive
    private int anio;
    @NotNull @NotEmpty @NotBlank
    private float importeNeto;
    @NotNull @NotEmpty @NotBlank
    private float importeBruto;
    @NotNull @NotEmpty @NotBlank
    private int isCuentaPropia;
    @Size(max = 5) @NotNull @NotEmpty @NotBlank
    private String iae;
    @Size(max = 10)
    private String cifEmpleador;
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaInicioEmpleo;


    public int getRentaId() {
        return rentaId;
    }

    public void setRentaId(int rentaId) {
        this.rentaId = rentaId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(float importeNeto) {
        this.importeNeto = importeNeto;
    }

    public float getImporteBruto() {
        return importeBruto;
    }

    public void setImporteBruto(float importeBruto) {
        this.importeBruto = importeBruto;
    }

    public int getIsCuentaPropia() {
        return isCuentaPropia;
    }

    public void setIsCuentaPropia(int isCuentaPropia) {
        this.isCuentaPropia = isCuentaPropia;
    }

    public String getIae() {
        return iae;
    }

    public void setIae(String iae) {
        this.iae = iae;
    }

    public String getCifEmpleador() {
        return cifEmpleador;
    }

    public void setCifEmpleador(String cifEmpleador) {
        this.cifEmpleador = cifEmpleador;
    }

    public Date getFechaInicioEmpleo() {
        return fechaInicioEmpleo;
    }

    public void setFechaInicioEmpleo(Date fechaInicioEmpleo) {
        this.fechaInicioEmpleo = fechaInicioEmpleo;
    }
}
