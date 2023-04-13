package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Esta clase hace dos consultas referentes a las preaprobaciones para las solicitudes
 *
 * @author alvaro.aleman@babelgroup.com, ramon.vazquez@babelgroup.com
 */
@Mapper
public interface ApprovalClienteMapper {
    /**
     * Consulta que devuelve un count de las solicitudes que se han encontrado por ese Id de persona si ha sido aprobado con garantias
     *
     * @param personaId id de la persona, para comprobar si tiene solicitudes
     * @return 0 si no ha encontrado solicitudes y cualquier otro número refiriendose a las solicitudes que tiene con su id
     */
    @Select("Select COUNT(SOLICITUD_ID) from SCORING.SOLICITUD_RENTING where PERSONA_ID = #{personaId} AND COD_RESOLUCION NOT LIKE 'AG%' AND FECHA_RESOLUCION <= ADD_MONTHS(SYSDATE,-24);")
    int existeClienteAprobadoConGarantias(int personaId);

    /**
     * Consulta que devuelve un count de las solicitudes que se han encontrado por ese Id de persona si ha sido rechazado previamente
     *
     * @param personaId id de la persona, para comprobar si tiene solicitudes
     * @return 0 si no ha encontrado solicitudes y cualquier otro número refiriendose a las solicitudes que tiene con su id
     */
    @Select("Select COUNT(SOLICITUD_ID) from SCORING.SOLICITUD_RENTING where PERSONA_ID = #{personaId} AND COD_RESOLUCION NOT LIKE 'DA%' AND FECHA_RESOLUCION <= ADD_MONTHS(SYSDATE,-24);")
    int existeClienteRechazadoPreviamente(int personaId);
}