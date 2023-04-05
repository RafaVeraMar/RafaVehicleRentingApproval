package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Direccion;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DireccionMapper {
    @Insert("INSERT INTO DIRECCION (TIPO_VIA_ID, NOMBRE_CALLE, NUM, PISO, PUERTA, ESCALERA, OTRO_DATO, COD_POSTAL, MUNICIPIO, COD_PROVINCIA) VALUES (#{tipoViaId.tipoViaId}, #{nombreCalle}, #{numero}, #{piso, jdbcType=VARCHAR}, null, null, null, #{codPostal}, #{municipio}, #{provincia.codProvincia})")
    @Options(useGeneratedKeys = true, keyProperty = "direccionId", keyColumn = "DIRECCION_ID")
    void insertDireccion(Direccion direccion);

    @Select("SELECT COUNT(DIRECCION_ID) FROM DIRECCION WHERE DIRECCION_ID = #{direccionId}")
    int existeDireccion(int direccionId);

    @Delete("UPDATE DIRECCION SET TIPO_VIA_ID=#{tipoViaId.tipoViaId},NOMBRE_CALLE=#{nombreCalle},NUM=#{numero},PISO=#{piso},PUERTA=#{puerta},ESCALERA=#{escalera}, OTRO_DATO=#{otroDato},COD_POSTAL=#{codPostal},MUNICIPIO=#{municipio},COD_PROVINCIA=#{provincia.codProvincia} WHERE DIRECCION_ID = #{direccionId}")
    int updateDireccion(Direccion direccion);
}
