package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
/**
 * Esta clase sirve para hacer los accesos a la base de datos de las Solicitudes de Renting
 *
 * @author alvaro.dorado@babelgroup.com / ismael.mesa@babelgroup.com
 */
@Mapper
public interface AutomaticResultMapper {
    /**
     * Mapper con el que actualizamos en la base de datos el campo de COD_RESOLUCION con los posibles
     * valores que toma, a través del método.
     * @param solicitudRenting que es la solicitud la solicitud que vamos a actualizar
     */
    @Update("UPDATE SOLICITUD_RENTING SET COD_RESOLUCION='#{codResolucion}', fecha_Resolucion=SYS_DATE WHERE SOLICITUD_ID = #{solicitudId}")
    void updateCodResolucion(SolicitudRenting solicitudRenting);

}
