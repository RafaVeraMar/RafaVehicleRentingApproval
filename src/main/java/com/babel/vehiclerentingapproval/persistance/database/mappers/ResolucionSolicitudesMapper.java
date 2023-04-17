package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * Esta interfaz hace dos consultas referentes a los tipos de resolucion de solicitudes de la base de datos
 *
 * @author andres.guijarro@babelgroup.com
 */
@Mapper
public interface ResolucionSolicitudesMapper {
    /**
     * Consulta que devuelve una lista de los tipos de resolucion de solicitudes
     *
     * @return Lista de objetos ResolucionSolicitud
     * @see ResolucionSolicitud
     */
    @Select("SELECT COD_RESULTADO, DESCRIPCION FROM TIPO_RESULTADO_SOLICITUD")
    @Results({
            @Result(property = "codigoResultado" , column = "COD_RESULTADO"),
            @Result(property = "descripcion" , column = "DESCRIPCION")
    })
    List<ResolucionSolicitud> getTipoResolucionesSolicitudes();


}
