package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Renta;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RentaMapper {
    @Insert("INSERT INTO RENTA_ANUAL (PERSONA_ID,PROFESION_ID, ANIO, IMPORTE_NETO, IMPORTE_BRUTO, IS_CUENTA_PROPIA,IAE,CIF_EMPLEADOR,FECHA_INICIO_EMPLEO) VALUES(#{persona.personaId},#{profesion.profesionId},#{anio},#{importeNeto},#{importeBruto},#{isCuentaPropia},#{iae, jdbcType=CHAR},#{cifEmpleador},#{fechaInicioEmpleo, jdbcType=DATE})")
    @Options(useGeneratedKeys = true, keyProperty = "rentaId", keyColumn = "RENTA_ID")
    void addRenta(Renta renta);

    @Select("SELECT COUNT(RENTA_ID) FROM RENTA_ANUAL WHERE RENTA_ID = #{rentaId}")
    int existeRenta(int rentaId);
}
