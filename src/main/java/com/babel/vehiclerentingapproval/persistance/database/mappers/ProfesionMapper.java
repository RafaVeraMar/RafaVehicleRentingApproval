package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * Esta interfaz proporciona metodos de manejo de profesiones en la base de datos
 *
 * @author andres.guijarro@babelgroup.com
 */
@Mapper
public interface ProfesionMapper {
    /**
     * Consulta que devuelve el numero de profesiones encontradas en la base de datos
     *
     * @param profesionId id de la profesion a buscar
     * @return numero de profesiones encontradas dado un ID
     */
    @Select("SELECT COUNT(PROFESION_ID) FROM PROFESION WHERE PROFESION_ID = #{profesionId}")
    int existeProfesion(int profesionId);
}
