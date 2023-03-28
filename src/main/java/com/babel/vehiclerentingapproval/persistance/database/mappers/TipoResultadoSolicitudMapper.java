package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface TipoResultadoSolicitudMapper {

    @Select("SELECT DESCRIPCION FROM TIPO_RESULTADO_SOLICITUD WHERE COD_RESULTADO=#{codSolicitud}")
    String getEstadoSolicitud(String codSolicitud);
}
