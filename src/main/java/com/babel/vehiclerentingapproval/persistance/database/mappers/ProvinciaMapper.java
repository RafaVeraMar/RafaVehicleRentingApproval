package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Provincia;
import com.babel.vehiclerentingapproval.models.TipoVia;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Esta interfaz define un mapper que realiza las operaciones relacionadas con la provincia de la base de datos
 * @author enrique.munoz@babelgroup.com
 */

@Mapper
public interface ProvinciaMapper {
    /**
     * Este metodo inserta una provincia en la base de datos
     * @param provincia la provincia a insertar
     */
    @Insert("INSERT INTO PROVINCIA (COD_PROVINCIA, NOMBRE) VALUES (#{codProvincia}, #{nombre})")
    @Options(useGeneratedKeys = true, keyProperty = "codProvincia", keyColumn = "COD_PROVINCIA")
    void insertProvincia(Provincia provincia);

    /**
     * Este metodo realiza un select sobre la base de datos para obtener la provincia
     * @param codProvincia
     * @return
     */
    @Select("SELECT COD_PROVINCIA, NOMBRE FROM PROVINCIA WHERE COD_PROVINCIA = #{codProvincia}")
    Provincia getProvincia(String codProvincia);
}
