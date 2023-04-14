package com.babel.vehiclerentingapproval.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.util.Date;
/**
 * @author andres.guijarro@babelgroup.com
 * @author javier.roldan@babelgroup.com
 */
public class Renta {

    /**
     * Atributo que es un identifacdor de una renta
     */
    @NotNull @NotEmpty @NotBlank @Positive
    private int rentaId;
    /**
     * Parametro de tipo Persona que representa a una persona
     */
    @NotNull @NotEmpty @NotBlank
    private Persona persona;
    /**
     * Parametro de tipo Profesion que representa a una profesion
     */
    @NotNull @NotEmpty @NotBlank
    private Profesion profesion;
    /**
     * Atributo que representa el anio fiscal al que pertenece la renta
     */
    @NotNull @NotEmpty @NotBlank @Positive
    private int anio;
    /**
     * Atributo que representa el importe neto de una renta
     */
    @NotNull @NotEmpty @NotBlank
    private float importeNeto;
    /**
     * Atributo que representa el importe bruto de una renta
     */
    @NotNull @NotEmpty @NotBlank
    private float importeBruto;
    /**
     * Atributo que representa si tu la cuenta pertenece a la persona titular
     */
    @NotNull @NotEmpty @NotBlank
    private int isCuentaPropia;
    /**
     * Atributo que representa el impuesto sobre actividades económicas de una persona en una renta
     */
    @Size(max = 5) @NotNull @NotEmpty @NotBlank
    private String iae;
    /**
     * Atributo que representa el cif de empleador de una persona
     */
    @Size(max = 10)
    private String cifEmpleador;
    /**
     * Atributo que representa la fecha de inicio del empleo de la persona
     */
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaInicioEmpleo;

    /**
     * Metodo público que se encarga de mostrar el valor de la id de la renta
     * @return devuelve el id de la renta
     */
    public int getRentaId() {
        return rentaId;
    }
    /**
     * Metodo publico que se encarga de darle un valor al id de la renta
     * @param rentaId que es el id de la renta
     */
    public void setRentaId(int rentaId) {
        this.rentaId = rentaId;
    }
    /**
     * Metodo público que se encarga de mostrar la Persona asociada a la renta
     * @return devuelve la persona
     */
    public Persona getPersona() {
        return persona;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la persona asociada a la renta
     * @param persona a la que se le quiere dar un valor
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    /**
     * Metodo público que se encarga de mostrar la profesion asociada a la renta
     * @return devuelve la profesion
     */
    public Profesion getProfesion() {
        return profesion;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la prfoesion asociada a la renta
     * @param profesion a la que se le quiere dar un valor
     */
    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }
    /**
     * Metodo público que se encarga de mostrar el anio asociada a la renta
     * @return devuelve el anio
     */
    public int getAnio() {
        return anio;
    }
    /**
     * Metodo publico que se encarga de darle un valor al anio asociada a la renta
     * @param anio a la que se le quiere dar un valor
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
    /**
     * Metodo público que se encarga de mostrar el importe neto asociado a la renta
     * @return devuelve el importe neto
     */
    public float getImporteNeto() {
        return importeNeto;
    }
    /**
     * Metodo publico que se encarga de darle un valor al importe neto asociado a la renta
     * @param importeNeto a la que se le quiere dar un valor
     */
    public void setImporteNeto(float importeNeto) {
        this.importeNeto = importeNeto;
    }
    /**
     * Metodo público que se encarga de mostrar el importe bruto asociado a la renta
     * @return devuelve el importe bruto
     */
    public float getImporteBruto() {
        return importeBruto;
    }
    /**
     * Metodo publico que se encarga de darle un valor al importe bruto asociado a la renta
     * @param importeBruto a la que se le quiere dar un valor
     */
    public void setImporteBruto(float importeBruto) {
        this.importeBruto = importeBruto;
    }

    /**
     * Metodo público que se encarga de mostrar la cuenta propia asociado a la renta
     * @return devuelve la cuenta propia
     */
    public int getIsCuentaPropia() {
        return isCuentaPropia;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la cuenta propia asociado a la renta
     * @param isCuentaPropia a la que se le quiere dar un valor
     */
    public void setIsCuentaPropia(int isCuentaPropia) {
        this.isCuentaPropia = isCuentaPropia;
    }
    /**
     * Metodo público que se encarga de mostrar el iae asociado a la renta
     * @return devuelve el iae
     */
    public String getIae() {
        return iae;
    }
    /**
     * Metodo publico que se encarga de darle un valor al iae a la renta
     * @param iae a la que se le quiere dar un valor
     */
    public void setIae(String iae) {
        this.iae = iae;
    }
    /**
     * Metodo público que se encarga de mostrar el cid del empleador asociado a la renta
     * @return devuelve el cid del empleador
     */
    public String getCifEmpleador() {
        return cifEmpleador;
    }
    /**
     * Metodo publico que se encarga de darle un valor al cid del empleador a la renta
     * @param cifEmpleador a la que se le quiere dar un valor
     */
    public void setCifEmpleador(String cifEmpleador) {
        this.cifEmpleador = cifEmpleador;
    }
    /**
     * Metodo público que se encarga de mostrar la fecha de inicio de empleo asociado a la renta
     * @return devuelve la fecha de inicio de empleo
     */
    public Date getFechaInicioEmpleo() {
        return fechaInicioEmpleo;
    }
    /**
     * Metodo publico que se encarga de darle un valor a la fecha de inicio de empleo a la renta
     * @param fechaInicioEmpleo a la que se le quiere dar un valor
     */
    public void setFechaInicioEmpleo(Date fechaInicioEmpleo) {
        this.fechaInicioEmpleo = fechaInicioEmpleo;
    }
}
