package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Provincia;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
/**
 * Esta interfaz hace dos consultas referentes a las provincias
 *
 * @author enrique.munoz@babelgroup.com
 */
@Mapper
public interface ProvinciaMapper {
    /**
     * Consulta que inserta una provincia en la base de datos
     *
     * @param provincia Objeto Provincia a insertar
     * @see Provincia
     */
    @Insert("INSERT INTO PROVINCIA (COD_PROVINCIA, NOMBRE) VALUES (#{codProvincia}, #{nombre})")
    @Options(useGeneratedKeys = true, keyProperty = "codProvincia", keyColumn = "COD_PROVINCIA")
    void insertProvincia(Provincia provincia);
    /**
     * Consulta que recupera una provincia de la base de datos dado su codigo
     *
     * @param codProvincia Codigo de la provincia
     * @see Provincia
     * @return Objeto Provincia
     */
    @Select("SELECT COD_PROVINCIA, NOMBRE FROM PROVINCIA WHERE COD_PROVINCIA = #{codProvincia}")
    Provincia getProvincia(String codProvincia);
}
