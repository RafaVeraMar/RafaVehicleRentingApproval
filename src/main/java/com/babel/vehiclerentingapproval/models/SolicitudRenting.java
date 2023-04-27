package com.babel.vehiclerentingapproval.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * Esta clase modela la Solicitud de renting
 * @author ismael.mesa@babelgroup.com
 * @author miguel.sdela@babelgroup.com
 */
public class SolicitudRenting {
    /**
     * Identificador de la solicitud de renting
     */
    @Getter @Setter
    private int solicitudId;
    /**
     * Parametro de tipo Persona que representa a una persona
     */
    @Getter @Setter
    private Persona persona;
    /**
     * Parametro de tipo Date referido a la fecha de solicitud, con el formato dd-MM-yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    private Date fechaSolicitud;
    /**
     * Parametro para controlar el numero de vehiculos de tipo BigInteger
     */
    @Getter @Setter
    private BigInteger numVehiculos;
    /**
     * Parametro para controlar la inversion de la solicitud de renting
     */
    @Getter @Setter
    private Float inversion;
    /**
     * Parametro para controlar la cuota de la solicitud de renting
     */
    @Getter @Setter
    private Float cuota;
    /**
     * Parametro para controlar el plazo de la solicitud de renting
     */
    @Getter @Setter
    private BigInteger plazo;
    /**
     * Parametro de tipo Date referido a la fecha de inicio de vigor, con el formato dd-MM-yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    private Date fechaInicioVigor;
    /**
     * Parametro de tipo Date referido a la fecha de resolución, con el formato dd-MM-yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    private Date fechaResolucion;
    /**
     * Parametro de tipo TipoResultadoSolicitud referido al tipo de resultado de la solicitud de renting
     */
    @Getter @Setter
    private TipoResultadoSolicitud tipoResultadoSolicitud;

    @Override
    public String toString() {
        return "SolicitudRenting{" +
                "solicitudId=" + getSolicitudId() +
                ", persona=" + getPersona() +
                ", fechaSolicitud=" + getFechaSolicitud() +
                ", numVehiculos=" + getNumVehiculos() +
                ", inversion=" + getInversion() +
                ", cuota=" + getCuota() +
                ", plazo=" + getPlazo()+
                ", fechaInicioVigor=" + getFechaInicioVigor() +
                ", fechaResolucion=" + getFechaResolucion() +
                ", tipoResultadoSolicitud=" + getTipoResultadoSolicitud() +
                '}';
    }
}
