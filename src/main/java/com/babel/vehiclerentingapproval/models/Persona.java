package com.babel.vehiclerentingapproval.models;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author enrique.munoz@babelgroup.com
 */
public class Persona {
    /**
     * Identificador de persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    @Getter    @Setter
    private int personaId;
    /**
     * Parámetro de nombre de persona
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private String nombre;
    /**
     * Parámetro de primer apellido de persona
     */
    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private String apellido1;
    /**
     * Parámetro de segundo apellido de persona
     */
    @Size(max = 50)
    @Getter    @Setter
    private String apellido2;
    /**
     * Parámetro de tipo Dirección que representa la dirección de domicilio de una persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private Direccion direccionDomicilio;
    /**
     * Parámetro de tipo Dirección que representa la dirección de notificación de una persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private Direccion direccionNotificacion;
    /**
     * Parámetro para controlar si la dirección de notificación es la misma que la de domicilio
     */
    @Getter    @Setter
    private boolean direccionDomicilioSameAsNotificacion = true;
    /**
     * Parámetro referido al nif de una persona
     */
    @Size(max=10)
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private String nif;
    /**
     * Parámetro referido a la fecha de nacimiento de una persona
     */
    @PastOrPresent
    @Getter    @Setter
    private Date fechaNacimiento;
    /**
     * Parámetro referido a la nacionalidad de una persona
     */
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private Pais nacionalidad;
    /**
     * Parámetro referido a la puntuación de una persona
     */
    @Getter    @Setter
    private int scoring;
    /**
     * Parámetro referido a la fecha en la que se puntúa a una persona
     */
    @PastOrPresent
    @Getter    @Setter
    private Date fechaScoring;
    /**
     * Parámetro de lista de tipo TelefonoContacto que se refiere a los telefonos de una persona
     */
    @Getter    @Setter
    private List<TelefonoContacto> telefonos;
    /**
     * Parámetro de lista de tipo ProductoContratado que se refiere a los productos contratados de una persona
     */
    @Getter    @Setter
    private List<ProductoContratado> productosContratados;
    /**
     * Parámetro referido al email de una persona
     */
    @Getter    @Setter
    private String email;


    @Override
    public String toString() {
        return "Persona{" +
                "personaId=" + getPersonaId() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido1='" + getApellido1() + '\'' +
                ", apellido2='" + getApellido2() + '\'' +
                ", direccionDomicilioId=" + getDireccionDomicilio().getDireccionId() +
                ", direccionNotificacionId=" + getDireccionNotificacion().getDireccionId() +
                ", direccionDomicilioSameAsNotificacion=" + isDireccionDomicilioSameAsNotificacion() +
                ", nif='" + getNif() + '\'' +
                ", fechaNacimiento=" + getFechaNacimiento().toString() +
                ", nacionalidadIsoAlfa2=" + getNacionalidad().getIsoAlfa2() +
                ", scoring=" + getScoring() +
                ", fechaScoring=" + getFechaScoring().toString() +
                ", telefonos=" + getTelefonos().toString() +
                ", productosContratados=" + getProductosContratados().toString() +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}


