package com.babel.vehiclerentingapproval.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author ismael.mesa@babelgroup.com
 * @author miguel.sdela@babelgroup.com
 */
public class SolicitudRenting {
    /**
     * Identificador de la solicitud de renting
     */
    private int solicitudId;
    /**
     * Parametro de tipo Persona que representa a una persona
     */
    private Persona persona;
    /**
     * Parametro de tipo Date referido a la fecha de solicitud, con el formato dd-MM-yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaSolicitud;
    /**
     * Parametro para controlar el numero de vehiculos de tipo BigInteger
     */
    private BigInteger numVehiculos;
    /**
     * Parametro para controlar la inversion de la solicitud de renting
     */
    private Float inversion;
    /**
     * Parametro para controlar la cuota de la solicitud de renting
     */
    private Float cuota;
    /**
     * Parametro para controlar el plazo de la solicitud de renting
     */
    private BigInteger plazo;
    /**
     * Parametro de tipo Date referido a la fecha de inicio de vigor, con el formato dd-MM-yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaInicioVigor;
    /**
     * Parametro de tipo Date referido a la fecha de resolución, con el formato dd-MM-yyyy
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaResolucion;
    /**
     * Parametro de tipo TipoResultadoSolicitud referido al tipo de resultado de la solicitud de renting
     */
    private TipoResultadoSolicitud tipoResultadoSolicitud;

    /**
     * Metodo público que se encarga de mostrar el valor de la id de la solicitud de renting
     * @return devuelve el id de la solicitud de renting
     */
    public int getSolicitudId ( ) {
        return solicitudId;
    }

    /**
     * Metodo publico que se encarga de darle un valor al id de la solicitud de renting
     * @param solicitudId que es el id de la solicitud
     */

    public void setSolicitudId (int solicitudId) {
        this.solicitudId = solicitudId;
    }

    /**
     * Metodo público que se encarga de mostrar la Persona asociada a la solicitud
     * @return devuelve la persona
     */
    public Persona getPersona ( ) {
        return persona;
    }

    /**
     * Metodo publico que se encarga de darle un valor a la persona asociada a la solicitud de renting
     * @param persona a la que se le quiere dar un valor
     */

    public void setPersona (Persona persona) {
        this.persona = persona;
    }

    /**
     * Metodo público que se encarga de mostrar la fecha de solicitud de renting
     * @return devuelve la fecha de solictud
     */

    public Date getFechaSolicitud ( ) {
        return fechaSolicitud;
    }

    /**
     * Metodo publico que se encarga de darle un valor a la fecha de la solicitud de renting
     * @param fechaSolicitud a la cual se le quiere dar un valor
     */
    public void setFechaSolicitud (Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * Metodo público que se encarga de mostrar el numero de vehiculos asociados a la solicitud de renting
     * @return devuelve el numero de vehiculos
     */

    public BigInteger getNumVehiculos ( ) {
        return numVehiculos;
    }

    /**
     * Metodo publico que se encarga de darle un valor al numero de vehiculos asociados a la solicitud de renting
     * @param numVehiculos a los que queremos dar un valor
     */

    public void setNumVehiculos (BigInteger numVehiculos) {
        this.numVehiculos = numVehiculos;
    }

    /**
     * Metodo público que se encarga de mostrar la inversion asociada a la solicitud de renting
     * @return devuelve la inversion asociada a la solicitud de renting
     */

    public Float getInversion ( ) {
        return inversion;
    }

    /**
     * Metodo publico que se encarga de darle un valor a la inversion asociada a la solicitud de renting
     * @param inversion a la que le queremos dar un valor
     */

    public void setInversion (Float inversion) {
        this.inversion = inversion;
    }

    /**
     * Metodo público que se encarga de mostrar la cuota asociada a la solicitud de renting
     * @return devuelve la cuota asociada a la solicitud de renting
     */

    public Float getCuota ( ) {
        return cuota;
    }

    /**
     * Metodo publico que se encarga de darle un valor a la cuota asociada a la solicitud de renting
     * @param cuota a la que queremos dar un valor
     */

    public void setCuota (Float cuota) {
        this.cuota = cuota;
    }

    /**
     * Metodo público que se encarga de mostrar el plazo asociado a la solicitud de renting
     * @return devuelve el plazo asociado a la solicitud
     */

    public BigInteger getPlazo ( ) {
        return plazo;
    }

    /**
     * Metodo publico que se encarga de darle un valor al plazo asociado a la solicitud de renting
     * @param plazo al que queremos dar un valor
     */

    public void setPlazo (BigInteger plazo) {
        this.plazo = plazo;
    }

    /**
     * Metodo público que se encarga de mostrar la fecha de inicio asociada a la solicitud de renting
     * @return la fecha asociada a la solicitud
     */

    public Date getFechaInicioVigor ( ) {
        return fechaInicioVigor;
    }

    /**
     * Metodo publico que se encarga de darle un valor a la fecha asociada a la solicitud de renting
     * @param fechaInicioVigor a la que le queremos dar un valor
     */

    public void setFechaInicioVigor (Date fechaInicioVigor) {
        this.fechaInicioVigor = fechaInicioVigor;
    }

    /**
     * Metodo público que se encarga de mostrar la fecha de resolucion asociada a la solicitud de renting
     * @return devuelve la fecha de resolucion asociada a la solicitud
     */

    public Date getFechaResolucion ( ) {
        return fechaResolucion;
    }

    /**
     * Metodo publico que se encarga de darle un valor a la fecha de resolucion asociada a la solicitud de renting
     * @param fechaResolucion a la que queremos dar un valor
     */
    public void setFechaResolucion (Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    /**
     * Metodo público que se encarga de mostrar el tipo resultado de la solicitud de renting
     * @return devuelve el tipo de resultado asociado a la solicitud de renting
     */

    public TipoResultadoSolicitud getTipoResultadoSolicitud ( ) {
        return tipoResultadoSolicitud;
    }

    /**
     * Metodo publico que se encarga de darle un valor al tipo de resultado de la solicitud de renting
     * @param tipoResultadoSolicitud
     */

    public void setTipoResultadoSolicitud (TipoResultadoSolicitud tipoResultadoSolicitud) {
        this.tipoResultadoSolicitud = tipoResultadoSolicitud;
    }
}
