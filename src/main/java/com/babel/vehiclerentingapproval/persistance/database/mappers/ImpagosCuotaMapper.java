package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ImpagosCuotaMapper {

    @Select("SELECT CUOTA FROM SCORING.SOLICITUD_RENTING WHERE SOLICITUD_ID = #{solicitudId}")
    float obtenerCuotaSolicitud(int solicitudId);

    @Select("SELECT ii.IMPORTE FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.IMPAGO_INTERNO ii ON sr.PERSONA_ID = ii.PERSONA_ID  WHERE sr.SOLICITUD_ID = #{solicittudId}")
    float obtenerImporteImpagoSolicitud(int solicitudId);

}
