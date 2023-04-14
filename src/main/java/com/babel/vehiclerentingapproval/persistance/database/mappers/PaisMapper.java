package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Pais;
import com.babel.vehiclerentingapproval.models.Provincia;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
/**
 * Esta clase proporciona metodos de manejo de paises en la base de datos
 *
 * @author enrique.munoz@babelgroup.com
 */
@Mapper
public interface PaisMapper {
    /**
     * Consulta que inserta un Pais en la base de datos
     *
     * @param pais Objeto Pais a insertar
     * @see Pais
     */
    @Insert("INSERT INTO PAIS (ISO_ALFA_2, ISO_NUM_3, ISO_ALFA_3, NOMBRE, ORDEN) VALUES (#{isoAlfa_2}, #{isoNum_3}, #{isoAlfa_3}, #{nombre}, #{orden, jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "isoAlfa_2", keyColumn = "ISO_ALFA_2")
    void insertPais(Pais pais);
    /**
     * Consulta que recupera un Pais en la base de datos
     *
     * @param isoAlfa2 identificador ISO Alfa 2 del pais a recuperar
     * @see Pais
     * @return Pais recuperado de la base de datos
     */
    @Select("SELECT ISO_ALFA_2, ISO_NUM_3, ISO_ALFA_3, NOMBRE, ORDEN FROM PAIS WHERE ISO_ALFA_2 = #{isoAlfa2}")
    Pais getPais(String isoAlfa2);
}
