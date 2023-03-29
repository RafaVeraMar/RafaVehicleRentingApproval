package com.babel.vehiclerentingapproval.persistance.database.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InversionIngresosMapper {

    @Select("SELECT (INVERSION,IMPORTE_NETO) FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.RENTA_ANUAL ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = {#solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD);")
    float obtenerInversionSolicitud(int solicitudId);



}
