package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutomaticResultMapper {
    @Insert("INSERT INTO SOLICITUD_RENTING (COD_RESOLUCION) VALUES (AA)")
    void insertApproval(SolicitudRenting solicitudRenting);

    @Insert("INSERT INTO SOLICITUD_RENTING (COD_RESOLUCION) VALUES (PA)")
    void insertPendingResult(SolicitudRenting solicitudRenting);

    @Insert("INSERT INTO SOLICITUD_RENTING (COD_RESOLUCION) VALUES (DM)")
    void insertDeny(SolicitudRenting solicitudRenting);

}
