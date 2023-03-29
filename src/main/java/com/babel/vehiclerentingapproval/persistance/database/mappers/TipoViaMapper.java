package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Direccion;
import com.babel.vehiclerentingapproval.models.TipoVia;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TipoViaMapper {
    @Insert("INSERT INTO TIPO_VIA (TIPO_VIA_ID, DESCRIPCION) VALUES (#{tipoViaId}, #{descripcion})")
    @Options(useGeneratedKeys = true, keyProperty = "tipoViaId", keyColumn = "TIPO_VIA_ID")
    void insertTipoVia(TipoVia tipoVia);


    @Select("SELECT TIPO_VIA_ID, DESCRIPCION FROM TIPO_VIA WHERE TIPO_VIA_ID = #{tipoViaId}")
    TipoVia getTipoVia(int tipoViaId);
}
