package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Direccion;
import org.apache.ibatis.annotations.*;
/**
 * Esta interfaz proporciona metodos para el manejo de direcciones en la base de datos
 *
 * @author andres.guijarro@babelgroup.com
 */
@Mapper
public interface DireccionMapper {
    /**
     * Inserta una nueva direccion en la base de datos.
     *
     * @param direccion el objeto direccion a insertar
     * @see Direccion
     */
    @Insert("INSERT INTO DIRECCION (TIPO_VIA_ID, NOMBRE_CALLE, NUM, PISO, PUERTA, ESCALERA, OTRO_DATO, COD_POSTAL, MUNICIPIO, COD_PROVINCIA) VALUES (#{tipoViaId.tipoViaId}, #{nombreCalle}, #{numero}, #{piso, jdbcType=VARCHAR}, #{puerta, jdbcType=VARCHAR}, #{escalera, jdbcType=VARCHAR}, #{otroDato, jdbcType=VARCHAR}, #{codPostal}, #{municipio}, #{provincia.codProvincia})")
    @Options(useGeneratedKeys = true, keyProperty = "direccionId", keyColumn = "DIRECCION_ID")
    @Result(property = "codProvincia", column = "COD_PROVINCIA")
    void insertDireccion(Direccion direccion);


    /**
     * Devuelve el numero de direcciones encontradas dada una ID. Se usa como comprobacion.
     *
     * @param direccionId la ID de la direccion a consultar
     * @return numero de ocurrencias
     */
    @Select("SELECT COUNT(DIRECCION_ID) FROM DIRECCION WHERE DIRECCION_ID = #{direccionId}")
    int existeDireccion(int direccionId);
    /**
     * Actualiza una direccion
     *
     * @param direccion objeto direccion a actualizar
     * @return numero de direcciones insertadas
     * @see Direccion
     */
    @Delete("UPDATE DIRECCION SET TIPO_VIA_ID=#{tipoViaId.tipoViaId},NOMBRE_CALLE=#{nombreCalle},NUM=#{numero},PISO=#{piso},PUERTA=#{puerta},ESCALERA=#{escalera}, OTRO_DATO=#{otroDato},COD_POSTAL=#{codPostal},MUNICIPIO=#{municipio},COD_PROVINCIA=#{provincia.codProvincia} WHERE DIRECCION_ID = #{direccionId}")
    int updateDireccion(Direccion direccion);
}
