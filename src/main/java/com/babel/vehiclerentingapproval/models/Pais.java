package com.babel.vehiclerentingapproval.models;

public class Pais {
    private String isoAlfa_2;
    private Integer isoNum_3;
    private String isoAlfa_3;
    private String nombre;
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
