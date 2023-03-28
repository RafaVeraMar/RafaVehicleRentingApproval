package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Pais;
import com.babel.vehiclerentingapproval.models.Provincia;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PaisMapper {
    @Insert("INSERT INTO PAIS (ISO_ALFA_2, ISO_NUM_3, ISO_ALFA_3, NOMBRE, ORDEN) VALUES (#{isoAlfa_2}, #{isoNum_3}, #{isoAlfa_3}, #{nombre}, #{orden, jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "isoAlfa_2", keyColumn = "ISO_ALFA_2")
    void insertPais(Pais pais);
}
