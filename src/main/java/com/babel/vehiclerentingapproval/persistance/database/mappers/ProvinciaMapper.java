package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Provincia;
import com.babel.vehiclerentingapproval.models.TipoVia;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProvinciaMapper {
    @Insert("INSERT INTO PROVINCIA (COD_PROVINCIA, NOMBRE) VALUES (#{codProvincia}, #{nombre})")
    @Options(useGeneratedKeys = true, keyProperty = "codProvincia", keyColumn = "COD_PROVINCIA")
    void insertProvincia(Provincia provincia);

    @Select("SELECT COD_PROVINCIA, NOMBRE FROM PROVINCIA WHERE COD_PROVINCIA = #{codProvincia}")
    Provincia getProvincia(String codProvincia);
}
