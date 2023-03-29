package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScoringRatingMapper {


    @Select("SELECT p.SCORING FROM SCORING.PERSONA p INNER JOIN SCORING.SOLICITUD_RENTING sr ON p.PERSONA_ID = sr.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId}")
    float obtenercScoringPersona(int solicitudId);

}
