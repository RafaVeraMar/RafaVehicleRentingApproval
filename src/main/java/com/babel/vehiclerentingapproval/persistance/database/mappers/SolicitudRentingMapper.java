package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import org.apache.ibatis.annotations.*;

/**
 * Esta clase sirve para hacer los accesos a la base de datos de las Solicitudes de Renting
 *
 * @author miguel.sdela@babelgroup.com / javier.serrano@babelgroup.com / ramon.vazquez@babelgroup.com / alvaro.aleman@babelgroup.com / javier.roldan@babelgroup.com
 */
@Mapper
public interface SolicitudRentingMapper {
    final static String cancelar = "CA";

    @Update("UPDATE SOLICITUD_RENTING SET FECHA_RESOLUCION=SYSDATE, " +
            "COD_RESOLUCION='" + cancelar + "' where SOLICITUD_ID=#{solicitudId}")
    void cancelarSolicitud(SolicitudRenting solicitudRenting);

    /**
     * Consulta que devuelve una solicitud con sus campos.
     *
     * @param solicitudId Este parámetro es el id de la Solicitud que queremos ver
     * @return
     */
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
            "COD_RESOLUCION = #{nuevoEstado.codResultado,jdbcType=CHAR}" +
            "Where SOLICITUD_ID=#{solicitudId}")
    void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado);

    void modificaSolicitud(Integer solicitudId, SolicitudRenting nuevoRenting);

    @Insert("INSERT INTO SOLICITUD_RENTING (PERSONA_ID, FECHA_SOLICITUD, NUM_VEHICULOS, INVERSION, CUOTA, PLAZO, FECHA_INICIO_VIGOR, FECHA_RESOLUCION, " +
            "COD_RESOLUCION) VALUES (#{persona.personaId}, SYSDATE, #{numVehiculos}, #{inversion}, #{cuota}, #{plazo, jdbcType=INTEGER}, " +
            "#{fechaInicioVigor, jdbcType=DATE}, #{fechaResolucion,jdbcType=DATE}, #{tipoResultadoSolicitud.codResultado, jdbcType=CHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "solicitudId", keyColumn = "SOLICITUD_ID")
    void insertSolicitudRenting(SolicitudRenting solicitudRenting);

    /**
     * Consulta que comprueba si existe una solicitud por el ID proporcionado
     *
     * @param solicitudId
     * @return devuelve el número de solicitudes que se encuentran con ese ID, si sale 0 significa que no hay.
     */
    @Select("SELECT COUNT(SOLICITUD_ID) FROM SOLICITUD_RENTING WHERE SOLICITUD_ID = #{solicitudId}")
    int existeSolicitud(int solicitudId);

}
