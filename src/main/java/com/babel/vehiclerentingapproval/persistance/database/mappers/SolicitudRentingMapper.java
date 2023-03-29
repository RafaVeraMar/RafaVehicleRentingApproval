package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface SolicitudRentingMapper {

    @Insert("INSERT INTO SOLICITUD_RENTING (PERSONA_ID, FECHA_SOLICITUD, NUM_VEHICULOS, INVERSION, CUOTA, PLAZO, FECHA_INICIO_VIGOR, FECHA_RESOLUCION, " +
            "COD_RESOLUCION) VALUES (#{persona.personaId}, SYSDATE, #{numVehiculos}, #{inversion}, #{cuota}, #{plazo, jdbcType=INTEGER}, " +
            "#{fechaInicioVigor, jdbcType=DATE}, #{fechaResolucion,jdbcType=DATE}, #{tipoResultadoSolicitud.codResultado, jdbcType=CHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "solicitudId", keyColumn = "SOLICITUD_ID")
    void insertSolicitudRenting(SolicitudRenting solicitudRenting);

}
