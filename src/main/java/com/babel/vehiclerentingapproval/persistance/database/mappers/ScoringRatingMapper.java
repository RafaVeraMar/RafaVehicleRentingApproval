package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * Este mapper obtiene el scoring de una solicitud de renting
 *
 * @author ismael.mesa@babelgroup.com
 */
@Mapper
public interface ScoringRatingMapper {
    /**
     * Consulta que devuelve el scoring de una solicitud de renting
     *
     * @param solicitudRenting solicitudRenting
     * @see SolicitudRenting
     * @return float con el scoring
     */
    @Select("SELECT p.SCORING FROM SCORING.PERSONA p INNER JOIN SCORING.SOLICITUD_RENTING sr ON p.PERSONA_ID = sr.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId}")
    float obtenercScoringPersona(SolicitudRenting solicitudRenting);

}
