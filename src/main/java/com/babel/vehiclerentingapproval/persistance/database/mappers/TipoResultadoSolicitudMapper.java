package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface TipoResultadoSolicitudMapper {

    @Select("SELECT DESCRIPCION FROM TIPO_RESULTADO_SOLICITUD tresultado INNER JOIN SOLICITUD_RENTING tSolicitud ON tSolicitud.COD_RESOLUCION = tresultado.COD_RESULTADO  WHERE tSolicitud.SOLICITUD_ID=#{idSolicitud}")
    String getEstadoSolicitud(int idSolicitud);

    @Select("SELECT COUNT (DESCRIPCION) FROM TIPO_RESULTADO_SOLICITUD tresultado INNER JOIN SOLICITUD_RENTING tSolicitud ON tSolicitud.COD_RESOLUCION = tresultado.COD_RESULTADO  WHERE tSolicitud.SOLICITUD_ID=#{idSolicitud}")
    Integer existeCodigoResolucion(int idSolicitud);
}
