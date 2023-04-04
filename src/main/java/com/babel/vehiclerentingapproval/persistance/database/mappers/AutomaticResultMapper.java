package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AutomaticResultMapper {
    @Update("UPDATE SOLICITUD_RENTING SET (COD_RESOLUCION) VALUES (AA) WHERE SOLICITUD_ID = #{solicitudId}")
    void setApproval(SolicitudRenting solicitudRenting);

    @Update("UPDATE SOLICITUD_RENTING SET (COD_RESOLUCION) VALUES (PA) WHERE SOLICITUD_ID = #{solicitudId}")
    void setPendingResult(SolicitudRenting solicitudRenting);

    @Update("UPDATE SOLICITUD_RENTING SET (COD_RESOLUCION) VALUES (DM) WHERE SOLICITUD_ID = #{solicitudId}")
    void setDeny(SolicitudRenting solicitudRenting);

}
