package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * Esta interfaz sirve para hacer consultas en la base de datos para hacer las preaprobaciones de si un cliente
 * existe y es garante para una solicitud de renting.
 *
 * @author ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com
 */

@Mapper
public interface ClienteExistenteGaranteMapper {
    @Select("SELECT COUNT(GARANTIA_ID) FROM GARANTIA_SOLICITUD gs INNER JOIN SOLICITUD_RENTING sr ON gs.SOLICITUD_ID = sr.SOLICITUD_ID " +
            "WHERE sr.COD_RESOLUCION = 'AG' AND gs.TIPO_GARANTIA = 'PE' AND AVALISTA_NIF = '#{nifAvalista}'")
    int clienteEsGarante(String nifAvalista);

    @Select("SELECT COUNT(PERSONA_ID) FROM PERSONA p INNER JOIN PRODUCTO_CONTRATADO_PERSONA pcp ON p.PERSONA_ID = pcp.PERSONA_ID " +
            "INNER JOIN PRODUCTO_CONTRATADO pc ON pc.PRODUCTO_CONTRATADO_ID = pcp.PRODUCTO_CONTRATADO_ID " +
            "WHERE p.PERSONA_ID = 99 AND (pc.FECHA_ALTA < {#fechaSolicitud}" +
            " AND (pc.FECHA_BAJA IS NULL OR pc.FECHA_BAJA > {#fechaSolicitud}) ) " +
            " AND (pcp.FECHA_ALTA < {#fechaSolicitud} " +
            " AND (pcp.FECHA_BAJA IS NULL OR pcp.FECHA_BAJA > {#fechaSolicitud}) )")
    int existeCliente(Date fechaSolicitud);
}
