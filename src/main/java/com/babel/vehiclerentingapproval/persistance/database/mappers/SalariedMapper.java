package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalariedMapper {
    @Select("SELECT ra.CIF_EMPLEADOR FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.RENTA_ANUAL ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD)")
    String obtenerCIFSolicitud(int solicitudId);
    @Select("SELECT CIF FROM SCORING.INFORMA")
    List<String> obtenerCIFInforma();
}
