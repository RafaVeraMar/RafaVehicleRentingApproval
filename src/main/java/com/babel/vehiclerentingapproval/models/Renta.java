package com.babel.vehiclerentingapproval.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

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
    @Getter    @Setter
    private int rentaId;
    /**
     * Parametro de tipo Persona, que asocia a la persona con la renta
     */
    @Getter    @Setter
    @NotNull @NotEmpty @NotBlank
    private Persona persona;
    /**
     * Parametro de tipo Profesion, que asocia a la profesion con la renta
     */
    @Getter    @Setter
    @NotNull @NotEmpty @NotBlank
    private Profesion profesion;
    /**
     * Parametro de tipo int, referido al año de la renta
     */
    @Getter    @Setter
    @NotNull @NotEmpty @NotBlank @Positive
    private int anio;
    /**
     * Parametro de tipo float, que indica el importe neto de la renta
     */
    @Getter    @Setter
    @NotNull @NotEmpty @NotBlank
    private float importeNeto;
    /**
     * Parametro de tipo float, que indica el importe bruto de la renta
     */
    @Getter    @Setter
    @NotNull @NotEmpty @NotBlank
    private float importeBruto;
    /**
     * Parametro de tipo int, que comprueba si la cuenta es propia o no
     */
    @Getter    @Setter
    @NotNull @NotEmpty @NotBlank
    private int isCuentaPropia;
    /**
     * Parametro de tipo String que indica el iae de la renta
     */
    @Getter    @Setter
    @Size(max = 5) @NotNull @NotEmpty @NotBlank
    private String iae;
    /**
     * Parametro de tipo String que indica el cif del empleador
     */
    @Getter    @Setter
    @Size(max = 10)
    private String cifEmpleador;
    /**
     * Parametro de tipo Date, referido a la fecha de inicio de empleo
     */
    @Getter    @Setter
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaInicioEmpleo;


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
