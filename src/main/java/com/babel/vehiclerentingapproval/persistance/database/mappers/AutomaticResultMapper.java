package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AutomaticResultMapper {

    @Update("UPDATE SOLICITUD_RENTING SET COD_RESOLUCION='#{codResolucion}', fecha_Resolucion=SYS_DATE WHERE SOLICITUD_ID = #{solicitudId}")
    void updateCodResolucion(SolicitudRenting solicitudRenting);

}
