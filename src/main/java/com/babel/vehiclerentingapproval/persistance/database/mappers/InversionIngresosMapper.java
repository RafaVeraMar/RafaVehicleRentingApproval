package com.babel.vehiclerentingapproval.persistance.database.mappers;


import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * Esta interfaz ofrece operaciones sobre los ingresos de las solicitudes
 *
 * @author ismael.mesa@babelgroup.com, daniel.gallardo@babelgroup.com
 */
@Mapper
public interface InversionIngresosMapper {
    /**
     * Consulta que obtiene la inversion de una solicitud
     *
     * @param solicitudRenting objeto SolicitudRenting
     * @see SolicitudRenting
     * @return devuelve el valor de la inversión de una solicitud
     */
    @Select("SELECT INVERSION FROM SCORING.SOLICITUD_RENTING WHERE SOLICITUD_ID = #{solicitudId}")
    float obtenerInversionSolicitud(SolicitudRenting solicitudRenting);
    /**
     * Consulta que obtiene el importe neto de una renta
     *
     * @param solicitudRenting objeto SolicitudRenting
     * @see SolicitudRenting
     * @return devuelve el importe neto
     */
    @Select("SELECT ra.IMPORTE_NETO FROM SCORING.SOLICITUD_RENTING sr INNER JOIN SCORING.RENTA_ANUAL ra ON sr.PERSONA_ID = ra.PERSONA_ID WHERE sr.SOLICITUD_ID = #{solicitudId} AND ra.ANIO = EXTRACT(YEAR FROM sr.FECHA_SOLICITUD)")
    float obtenerImporteNetoRenta(SolicitudRenting solicitudRenting);

}
