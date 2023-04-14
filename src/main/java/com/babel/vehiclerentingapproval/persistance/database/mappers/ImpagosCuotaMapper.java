package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * Esta interfaz sirve ofrecer operaciones sobre las cuotas de impago en la base de datos
 *
 * @author daniel.gallardo@babelgroup.com, ismael.mesa@babelgroup.com
 */
@Mapper
public interface ImpagosCuotaMapper {
    /**
     * Consulta que recupera la cuota de una solicitud de renting
     *
     * @param solicitudRenting objeto SolicitudRenting
     * @see SolicitudRenting
     * @return devuelve la cuota de la solicitud proporcioanda
     */
    @Select("SELECT CUOTA FROM SCORING.SOLICITUD_RENTING WHERE SOLICITUD_ID = #{solicitudId}")
    float obtenerCuotaSolicitud(SolicitudRenting solicitudRenting);
    /**
     * Consulta que recuperael importe de impago interno
     *
     * @param solicitudRenting objeto SolicitudRenting
     * @see SolicitudRenting
     * @return devuelve el importe de impago interno de la solicitud proporcioanda
     */
    @Select("SELECT ii.IMPORTE FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.IMPAGO_INTERNO ii ON sr.PERSONA_ID = ii.PERSONA_ID  WHERE sr.SOLICITUD_ID = #{solicitudId}")
    float obtenerImporteImpagoInterno(SolicitudRenting solicitudRenting);

}
