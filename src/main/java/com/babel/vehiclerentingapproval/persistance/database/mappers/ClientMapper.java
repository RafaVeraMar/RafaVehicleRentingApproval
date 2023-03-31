package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Select;

public interface ClientMapper {

    @Select("SELECT ra.FECHA_ALTA FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.PRODUCTO_CONTRATADOR_PERSONA ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD)")
    float obtenerFechaAltaSolicitud(int solicitudId);

}
