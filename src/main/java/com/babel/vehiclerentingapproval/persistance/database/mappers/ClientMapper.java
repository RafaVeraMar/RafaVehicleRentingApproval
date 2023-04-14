package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Select;
/**
 * Esta clase sirve para hacer las operaciones relacionadas con las solicitudes de los clientes
 *
 * @author ismael.mesa@babelgroup.com, rafael.vera@babelgroup.com
 */
public interface ClientMapper {
    /**
     * Obtiene la fecha de alta de una solicitud
     *
     * @param solicitudId id de la solicitud de la que se quiere obtener la fecha de alta
     * @return fecha de alta
     */
    @Select("SELECT ra.FECHA_ALTA FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.PRODUCTO_CONTRATADOR_PERSONA ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD)")
    float obtenerFechaAltaSolicitud(int solicitudId);



}
