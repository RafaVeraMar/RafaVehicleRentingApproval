package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SolicitudRentingMapper {
    @Select("Select SOLICITUD_ID,PERSONA_ID,FECHA_SOLICITUD,NUM_VEHICULOS,INVERSION,CUOTA,PLAZO," +
            "FECHA_INICIO_VIGOR,FECHA_RESOLUCION,COD_RESOLUCION from SCORING.SOLICITUD_RENTING where SOLICITUD_ID = #{solicitudId}")
    @Results({
            @Result(property = "solicitudId", column = "SOLICITUD_ID"),
            @Result(property = "persona.personaId", column = "PERSONA_ID"),
            @Result(property = "fechaSolicitud", column = "FECHA_SOLICITUD"),
            @Result(property = "numVehiculos", column = "NUM_VEHICULOS"),
            @Result(property = "inversion", column = "INVERSION"),
            @Result(property = "cuota", column = "CUOTA"),
            @Result(property = "plazo", column = "PLAZO"),
            @Result(property = "fechaInicioVigor", column = "FECHA_INICIO_VIGOR"),
            @Result(property = "fechaResolucion", column = "FECHA_RESOLUCION"),
            @Result(property = "tipoResultadoSolicitud.codResultado", column = "COD_RESOLUCION"),
    })
    SolicitudRenting getSolicitudByID(int solicitudId);

    @Update("Update SOLICITUD_RENTING SET " +
            "NUM_VEHICULOS = #{nuevoRenting.numVehiculos}, " +
            "INVERSION = #{nuevoRenting.inversion}," +
            "CUOTA = #{nuevoRenting.cuota}," +
            "PLAZO = #{nuevoRenting.plazo, jdbcType=FLOAT}," +
            "FECHA_INICIO_VIGOR = #{nuevoRenting.fechaInicioVigor,jdbcType=DATE}," +
            "FECHA_RESOLUCION = #{nuevoRenting.fechaResolucion,jdbcType=DATE}, " +
            "COD_RESOLUCION = #{nuevoRenting.tipoResultadoSolicitud.codResultado,jdbcType=CHAR}" +
            "Where SOLICITUD_ID=#{solicitudId}")
    void modificaSolicitud(Integer solicitudId, SolicitudRenting nuevoRenting);
    @Insert("INSERT INTO SOLICITUD_RENTING (PERSONA_ID, FECHA_SOLICITUD, NUM_VEHICULOS, INVERSION, CUOTA, PLAZO, FECHA_INICIO_VIGOR, FECHA_RESOLUCION, " +
            "COD_RESOLUCION) VALUES (#{persona.personaId}, SYSDATE, #{numVehiculos}, #{inversion}, #{cuota}, #{plazo, jdbcType=INTEGER}, " +
            "#{fechaInicioVigor, jdbcType=DATE}, #{fechaResolucion,jdbcType=DATE}, #{tipoResultadoSolicitud.codResultado, jdbcType=CHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "solicitudId", keyColumn = "SOLICITUD_ID")
    void insertSolicitudRenting(SolicitudRenting solicitudRenting);

}
