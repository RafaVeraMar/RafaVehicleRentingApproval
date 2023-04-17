package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.TelefonoContacto;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 * Esta interfaz proporciona metodos CRUD para los teléfonos
 *
 * @author tomas.prados@babelgroup.com, enrique.munoz@babelgroup.com
 */
@Mapper
public interface TelefonoMapper {
    /**
     * Consulta que devuelve la lista de telefonos de una persona
     *
     * @param personaId id de la persona
     * @return lista de telefonos que tiene una persona
     */
    @Select("Select TELEFONO_ID, TELEFONO FROM TELEFONO_CONTACTO WHERE PERSONA_ID = #{personaId}")
    @Result(property = "telefonoId", column = "TELEFONO_ID")
    @Result(property = "telefono", column = "TELEFONO")
    List<TelefonoContacto> listarTelefonos(Integer personaId);
    /**
     * Consulta que añade un telefono
     *
     * @param telefono objeto TelefonoContacto
     * @param persona objeto Persona
     */
    @Insert("INSERT INTO TELEFONO_CONTACTO (PERSONA_ID, TELEFONO) VALUES(#{persona.personaId},#{telefono.telefono})")
    @Options(useGeneratedKeys = true, keyProperty = "telefono.telefonoId", keyColumn = "TELEFONO_ID")
    void addTelefono(TelefonoContacto telefono, Persona persona);
    /**
     * Consulta que actualiza un telefono
     *
     * @param telefono objeto TelefonoContacto
     * @param personaId ID de la persona dueña del telefono
     */
    @Update("Update TELEFONO_CONTACTO SET TELEFONO=#{telefono.telefono} WHERE PERSONA_ID = #{personaId} AND TELEFONO_ID = #{telefono.telefonoId}")
    void updateTelefono(Integer personaId, TelefonoContacto telefono);
    /**
     * Consulta que elimina un telefono
     *
     * @param telefono objeto TelefonoContacto
     * @param personaId ID de la persona dueña del telefono
     */
    @Delete("DELETE FROM TELEFONO_CONTACTO WHERE TELEFONO_ID = #{telefono.telefonoId} AND PERSONA_ID = #{personaId}")
    void deleteTelefono(Integer personaId, TelefonoContacto telefono);
    /**
     * Consulta que elimina TODOS los telefonos de una persona
     *
     * @param personaId ID de la persona dueña de los telefono
     */
    @Delete("DELETE FROM TELEFONO_CONTACTO WHERE PERSONA_ID = #{personaId}")
    void deleteTelefonosByIdPersona(Integer personaId);
}
