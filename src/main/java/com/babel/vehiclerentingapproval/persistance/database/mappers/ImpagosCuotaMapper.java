package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ImpagosCuotaMapper {

    @Select("SELECT CUOTA FROM SCORING.SOLICITUD_RENTING WHERE SOLICITUD_ID = #{solicitudId}")
    float obtenerCuotaSolicitud(SolicitudRenting solicitudRenting);

    @Select("SELECT ii.IMPORTE FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.IMPAGO_INTERNO ii ON sr.PERSONA_ID = ii.PERSONA_ID  WHERE sr.SOLICITUD_ID = #{solicitudId}")
    float obtenerImporteImpagoInterno(SolicitudRenting solicitudRenting);

}
