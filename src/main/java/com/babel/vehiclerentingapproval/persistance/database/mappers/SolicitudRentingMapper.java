package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SolicitudRentingMapper {
    final static String cancelar = "CA";

    @Update("UPDATE SOLICITUD_RENTING SET FECHA_RESOLUCION=SYSDATE, " +
            "COD_RESOLUCION='" + cancelar + "' where SOLICITUD_ID=#{solicitudId}")
    void cancelarSolicitud (SolicitudRenting solicitudRenting);

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
    SolicitudRenting getSolicitudByID (int solicitudId);

    @Update("Update SOLICITUD_RENTING SET " +
            "COD_RESOLUCION = #{nuevoEstado.codResultado,jdbcType=CHAR}" +
            "Where SOLICITUD_ID=#{solicitudId}")
    void modificaEstadoSolicitud (Integer solicitudId, TipoResultadoSolicitud nuevoEstado);

    /**
     * Inserta una nueva solicitud de renting en la base de datos.
     * Este método utiliza una sentencia SQL 'INSERT' para almacenar la información de la solicitud de renting en la tabla SOLICITUD_RENTING.
     * La fecha de la solicitud se establece automáticamente en la fecha actual del sistema (SYSDATE).
     *
     * @param solicitudRenting el objeto SolicitudRenting que contiene la información de la solicitud de renting a insertar
     */
    @Insert("INSERT INTO SOLICITUD_RENTING (PERSONA_ID, FECHA_SOLICITUD, NUM_VEHICULOS, INVERSION, CUOTA, PLAZO, FECHA_INICIO_VIGOR, FECHA_RESOLUCION, " +
            "COD_RESOLUCION) VALUES (#{persona.personaId}, SYSDATE, #{numVehiculos}, #{inversion}, #{cuota}, #{plazo, jdbcType=INTEGER}, " +
            "#{fechaInicioVigor, jdbcType=DATE}, #{fechaResolucion,jdbcType=DATE}, #{tipoResultadoSolicitud.codResultado, jdbcType=CHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "solicitudId", keyColumn = "SOLICITUD_ID")
    void insertSolicitudRenting (SolicitudRenting solicitudRenting);

    @Select("SELECT COUNT(SOLICITUD_ID) FROM SOLICITUD_RENTING WHERE SOLICITUD_ID = #{solicitudId}")
    int existeSolicitud (int solicitudId);

}
