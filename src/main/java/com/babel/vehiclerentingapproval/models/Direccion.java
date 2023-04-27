package com.babel.vehiclerentingapproval.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author javier.roldan@babelgroup.com
 * @author andres.guijarro@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
public class Direccion {
    /**
     * Identificador de la direccion
     */
    @NotNull
    @Getter @Setter
    private int direccionId;
    /**
     * Identificador del tipo de via
     */
    @NotNull
    @Getter @Setter
    private TipoVia tipoViaId;
    /**
     * Parametro para almacenar el nombre de la calle
     */
    @NotNull
    @Size(max=50)
    @Getter @Setter
    private String nombreCalle;
    /**
     * Parametro para almacenar el numero de la calle
     */
    @NotNull
    @Size(max=10)
    @Getter @Setter
    private String numero;
    /**
     * Parametro para almacenar el piso del domicilio
     */
    @Size(max=10)
    @Getter @Setter
    private String piso;
    /**
     * Parametro para almacenar la puerta del domicilio
     */
    @Size(max=10)
    @Getter @Setter
    private String puerta;
    /**
     * Parametro para almacenar la escalera del domicilio
     */
    @Size(max=10)
    @Getter @Setter
    private String escalera;
    /**
     * Parametro para almacenar otros datos del domicilio
     */
    @Size(max=100)
    @Getter @Setter
    private String otroDato;
    /**
     * Parametro para almacenar el codigo postal del domicilio
     */
    @NotNull
    @Size(max=5)
    @Getter @Setter
    private String codPostal;
    /**
     * Parametro para almacenar el municipio del domicilio
     */
    @NotNull
    @Size(max=50)
    @Getter @Setter
    private String municipio;
    /**
     * Parametro para almacenar la provincia del domicilio
     */
    @NotNull
    @Size(max=2)
    @Getter @Setter
    private Provincia provincia;



    @Override
    public String toString() {
        return "Direccion{" +
                "direccionId=" + this.getDireccionId() +
                ", tipoViaId=" + this.getTipoViaId().getTipoViaId() +'\'' +
                ", descripcionTipoVia=" + this.getTipoViaId().getDescripcion() +'\'' +
                ", nombreCalle='" + this.getNombreCalle() + '\'' +
                ", numero='" + this.getNumero() + '\'' +
                ", piso='" + this.getPiso() + '\'' +
                ", puerta='" + this.getPuerta() + '\'' +
                ", escalera='" + this.getEscalera() + '\'' +
                ", otroDato='" + this.getOtroDato() + '\'' +
                ", codPostal='" + this.getCodPostal() + '\'' +
                ", municipio='" + this.getMunicipio() + '\'' +
                ", codProvincia=" + this.getProvincia().getCodProvincia() +'\'' +
                ", codProvincia=" + this.getProvincia().getNombre() +'\'' +
                '}';
    }
}
