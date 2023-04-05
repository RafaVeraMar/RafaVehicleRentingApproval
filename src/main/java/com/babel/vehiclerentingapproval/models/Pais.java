package com.babel.vehiclerentingapproval.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Pais {
    @NotNull
    @Size(max=2)
    private String isoAlfa_2;
    @NotNull
    @Size(max=38)
    private Integer isoNum_3;
    @NotNull
    @Size(max=3)
    private String isoAlfa_3;
    @NotNull
    @Size(max=100)
    private String nombre;
    @Size(max=38)
    private Integer orden;

    public Pais(String isoAlfa_2, Integer isoNum_3, String isoAlfa_3, String nombre, Integer orden) {
        this.isoAlfa_2 = isoAlfa_2;
        this.isoNum_3 = isoNum_3;
        this.isoAlfa_3 = isoAlfa_3;
        this.nombre = nombre;
        this.orden = orden;
    }

    public String getIsoAlfa_2() {
        return isoAlfa_2;
    }

    public void setIsoAlfa_2(String isoAlfa_2) {
        this.isoAlfa_2 = isoAlfa_2;
    }

    public Integer getIsoNum_3() {
        return isoNum_3;
    }

    public void setIsoNum_3(Integer isoNum_3) {
        this.isoNum_3 = isoNum_3;
    }

    public String getIsoAlfa_3() {
        return isoAlfa_3;
    }

    public void setIsoAlfa_3(String isoAlfa_3) {
        this.isoAlfa_3 = isoAlfa_3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
