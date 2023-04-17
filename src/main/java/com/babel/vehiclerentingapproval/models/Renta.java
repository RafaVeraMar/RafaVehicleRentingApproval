package com.babel.vehiclerentingapproval.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Clase que modela el objeto de Renta
 * @author andres.guijarro@babelgroup.com
 * @author javier.roldan@babelgroup.com
 */
public class Renta {
    /**
     * Parametro que indica el identificador de la renta
     */
    @NotNull @NotEmpty @NotBlank @Positive
    private int rentaId;
    /**
     * Parametro de tipo Persona, que asocia a la persona con la renta
     */
    @NotNull @NotEmpty @NotBlank
    private Persona persona;
    /**
     * Parametro de tipo Profesion, que asocia a la profesion con la renta
     */
    @NotNull @NotEmpty @NotBlank
    private Profesion profesion;
    /**
     * Parametro de tipo int, referido al año de la renta
     */
    @NotNull @NotEmpty @NotBlank @Positive
    private int anio;
    /**
     * Parametro de tipo float, que indica el importe neto de la renta
     */
    @NotNull @NotEmpty @NotBlank
    private float importeNeto;
    /**
     * Parametro de tipo float, que indica el importe bruto de la renta
     */
    @NotNull @NotEmpty @NotBlank
    private float importeBruto;
    /**
     * Parametro de tipo int, que comprueba si la cuenta es propia o no
     */
    @NotNull @NotEmpty @NotBlank
    private int isCuentaPropia;
    /**
     * Parametro de tipo String que indica el iae de la renta
     */
    @Size(max = 5) @NotNull @NotEmpty @NotBlank
    private String iae;
    /**
     * Parametro de tipo String que indica el cif del empleador
     */
    @Size(max = 10)
    private String cifEmpleador;
    /**
     * Parametro de tipo Date, referido a la fecha de inicio de empleo
     */
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaInicioEmpleo;

    /**
     * Metodo público que se encarga de mostrar el valor del identificador de la renta, de tipo int
     * @return devuelve el identificador asociado a la renta
     */
    public int getRentaId() {
        return rentaId;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al identificador de la renta
     * @param rentaId es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setRentaId(int rentaId) {
        this.rentaId = rentaId;
    }
    /**
     * Metodo público que se encarga de mostrar el valor de la persona asociada a la renta
     * @return devuelve la persona asociada a la renta
     */
    public Persona getPersona() {
        return persona;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la persona asociada a la renta
     * @param persona es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    /**
     * Metodo público que se encarga de mostrar el valor de la profesion asociada a la renta
     * @return devuelve la profesion asociada a la renta
     */
    public Profesion getProfesion() {
        return profesion;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la profesion asociada a la renta
     * @param profesion es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del año asociada a la renta
     * @return devuelve el año asociado a la renta
     */
    public int getAnio() {
        return anio;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al año asociado a la renta
     * @param anio es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del importe neto asociado a la renta
     * @return devuelve el importe neto asociado a la renta
     */
    public float getImporteNeto() {
        return importeNeto;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al importe neto asociado a la renta
     * @param importeNeto es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setImporteNeto(float importeNeto) {
        this.importeNeto = importeNeto;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del importe bruto asociado a la renta
     * @return devuelve el importe bruto asociado a la renta
     */
    public float getImporteBruto() {
        return importeBruto;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al importe bruto asociado a la renta
     * @param importeBruto es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setImporteBruto(float importeBruto) {
        this.importeBruto = importeBruto;
    }
    /**
     * Metodo público que se encarga de mostrar el valor de si es cuenta propia o no, asociado a la renta
     * @return devuelve el valor de si es cuenta propia o no asociado a la renta
     */
    public int getIsCuentaPropia() {
        return isCuentaPropia;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al isCuentaPropia asociado a la renta
     * @param isCuentaPropia es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setIsCuentaPropia(int isCuentaPropia) {
        this.isCuentaPropia = isCuentaPropia;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del iae, asociado a la renta
     * @return devuelve el valor del iae asociado a la renta
     */
    public String getIae() {
        return iae;
    }
    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al iae, asociado a la renta
     * @param iae es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setIae(String iae) {
        this.iae = iae;
    }
    /**
     * Metodo público que se encarga de mostrar el valor del cif del empleador, asociado a la renta
     * @return devuelve el valor del cif de empleador asociado a la renta
     */
    public String getCifEmpleador() {
        return cifEmpleador;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro al cif del empleador, asociado a la renta
     * @param cifEmpleador es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setCifEmpleador(String cifEmpleador) {
        this.cifEmpleador = cifEmpleador;
    }

    /**
     * Metodo público que se encarga de mostrar el valor de la fecha de inicio de empleo, asociado a la renta
     * @return devuelve el valor de la fecha de inicio de empleo, asociado a la renta
     */
    public Date getFechaInicioEmpleo() {
        return fechaInicioEmpleo;
    }

    /**
     * Metodo publico que se encarga de asignar el valor pasado como parametro a la fecha de inicio del empleo, asociado a la renta
     * @param fechaInicioEmpleo es el valor que se le va a asignar al parametro indicado del objeto renta
     */
    public void setFechaInicioEmpleo(Date fechaInicioEmpleo) {
        this.fechaInicioEmpleo = fechaInicioEmpleo;
    }

    @Override
    public String toString() {
        return "Renta{" +
                "rentaId=" + getRentaId() +
                ", persona=" + getPersona() +
                ", profesion=" + getProfesion() +
                ", anio=" + getAnio() +
                ", importeNeto=" + getImporteNeto() +
                ", importeBruto=" + getImporteBruto() +
                ", isCuentaPropia=" + getIsCuentaPropia() +
                ", iae='" + getIae() + '\'' +
                ", cifEmpleador='" + getCifEmpleador() + '\'' +
                ", fechaInicioEmpleo=" + getFechaInicioEmpleo() +
                '}';
    }
}
