package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Renta;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
/**
 * Esta clase sirve para hacer los accesos a la base de datos de renta
 *
 * @author andres.guijarro@babelgroup.com
 */
@Mapper
public interface RentaMapper {
    /**
     * Inserta una nueva renta en la base de datos.
     * Este método utiliza una sentencia SQL 'INSERT' para almacenar la información de la renta en la tabla RENTA_ANUAL.
     * @param renta el objeto renta que contiene la información de la renta a insertar
     */
    @Insert("INSERT INTO RENTA_ANUAL (PERSONA_ID,PROFESION_ID, ANIO, IMPORTE_NETO, IMPORTE_BRUTO, IS_CUENTA_PROPIA,IAE,CIF_EMPLEADOR,FECHA_INICIO_EMPLEO) VALUES(#{persona.personaId},#{profesion.profesionId},#{anio},#{importeNeto},#{importeBruto},#{isCuentaPropia},#{iae, jdbcType=CHAR},#{cifEmpleador},#{fechaInicioEmpleo, jdbcType=DATE})")
    @Options(useGeneratedKeys = true, keyProperty = "rentaId", keyColumn = "RENTA_ID")
    void addRenta(Renta renta);
    /**
     * Consulta que comprueba si existe una renta por el ID proporcionado
     *
     * @param rentaId
     * @return devuelve el número de rentas que se encuentran con ese ID, si sale 0 significa que no hay.
     */
    @Select("SELECT COUNT(RENTA_ID) FROM RENTA_ANUAL WHERE RENTA_ID = #{rentaId}")
    int existeRenta(int rentaId);
}
