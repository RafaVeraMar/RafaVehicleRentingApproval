package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Esta clase sirve para obtener la fecha de inicio de empleo de una solicitud
 *
 * @author ismael.mesa@babelgroup.com, rafael.vera@babelgroup.com
 */
@Mapper
public interface EmploymentSeniorityMapper {
    /**
     * Obtiene la fecha de inicio de empleo de una solicitud
     *
     * @param solicitudRenting Objeto SolicitudRenting
     * @see SolicitudRenting
     * @return fecha de inicio de empleo de la solicitud
     */
    //SELECT (TO_DATE(CURRENT_DATE) - FECHA_INICIO_EMPLEO)/365 AS datediffYears FROM SCORING.RENTA_ANUAL ra WHERE RENTA_ID = 2
    @Select("SELECT(TO_DATE(CURRENT_DATE) - ra.FECHA_INICIO_EMPLEO)/365 AS datediffYears FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.RENTA_ANUAL ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD)")
    float obtenerFechaInicioEmpleoSolicitud(SolicitudRenting solicitudRenting);



}
