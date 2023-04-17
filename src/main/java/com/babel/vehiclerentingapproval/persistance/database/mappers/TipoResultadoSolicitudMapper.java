package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Esta interfaz define el mapper para poder interactuar con el modelo de TipoResultado de la base de datos
 * @author javier.serrano@babelgroup.com, javier.roldan@babelgroup.com
 */
@Mapper
public interface TipoResultadoSolicitudMapper {

    /**
     * Extrae la descripción de la resolución de una solicitud de renting
     * @param idSolicitud ID de la solicitud de renting
     * @return Devuelve la descripcion de la resolución como cadena de caracteres
     */
    @Select("SELECT DESCRIPCION FROM TIPO_RESULTADO_SOLICITUD tresultado INNER JOIN SOLICITUD_RENTING tSolicitud ON tSolicitud.COD_RESOLUCION = tresultado.COD_RESULTADO  WHERE tSolicitud.SOLICITUD_ID=#{idSolicitud}")
    String getEstadoSolicitud(int idSolicitud);

    /**
     * Comprueba si existe la id de la solicitud o si el codigo de la resolucion es nulo
     * @param idSolicitud ID de la solicitud de renting
     * @return Devuelve 0 si la id no existe o el codigo de resolucion es nulo
     */
    @Select("SELECT COUNT (DESCRIPCION) FROM TIPO_RESULTADO_SOLICITUD tresultado INNER JOIN SOLICITUD_RENTING tSolicitud ON tSolicitud.COD_RESOLUCION = tresultado.COD_RESULTADO  WHERE tSolicitud.SOLICITUD_ID=#{idSolicitud}")
    int existeCodigoResolucion(int idSolicitud);

    @Select("SELECT COD_RESULTADO FROM TIPO_RESULTADO_SOLICITUD")
    List<String> getListaEstados();

    /**
     * Extrae los datos de la resolución de una solicitud de renting
     * @param idSolicitud ID de la solicitud de renting de la que extraer los datos de la resolucion
     * @return Devuelve un objeto de tipo resultado con los datos asociados a la resolucón de una solicitud de renting
     * @see TipoResultadoSolicitud
     */
    @Select("SELECT tresultado.COD_RESULTADO, tresultado.DESCRIPCION FROM TIPO_RESULTADO_SOLICITUD tresultado INNER JOIN SOLICITUD_RENTING tSolicitud ON tSolicitud.COD_RESOLUCION = tresultado.COD_RESULTADO WHERE tSolicitud.SOLICITUD_ID=#{idSolicitud} ")
    
    @Result(property = "codResultado" , column = "COD_RESULTADO")
    @Result(property = "descripcion" , column = "DESCRIPCION")

    TipoResultadoSolicitud getResultadoSolicitud(int idSolicitud);

    /**
     * Comprueba si ese codigo de resolucion está en la base de datos
     * @param cod El codigo de resolución a comprobar
     * @return Devuelve el entero 1 si lo ha encontrado
     */
    @Select("SELECT COUNT (COD_RESULTADO) FROM TIPO_RESULTADO_SOLICITUD WHERE COD_RESULTADO = #{cod}")
    int codigoValido(String cod);
}
