package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * Esta clase hace dos consultas referentes a las CIF de los Scorings
 *
 * @author alvaro.dorado@babelgroup.com, ismael.mesa@babelgroup.com
 */
@Mapper
public interface SalariedMapper {
    /**
     * Consulta que devuelve el CIF de una solicitud
     *
     * @param solicitudRenting Objeto solicitudRenting
     * @see SolicitudRenting
     * @return el CIF de la solicitud
     */
    @Select("SELECT ra.CIF_EMPLEADOR FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.RENTA_ANUAL ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD)")
    String obtenerCIFSolicitud(SolicitudRenting solicitudRenting);
    /**
     * Consulta que devuelve una lista de CIF existentes
     *
     * @return lista de CIFs existentes
     */
    @Select("SELECT CIF FROM SCORING.INFORMA")
    List<String> obtenerCIFInforma();
}
