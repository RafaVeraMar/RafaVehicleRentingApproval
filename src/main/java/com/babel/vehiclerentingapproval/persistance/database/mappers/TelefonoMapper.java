package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.TelefonoContacto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TelefonoMapper {

    @Select("Select TELEFONO_ID, TELEFONO FROM TELEFONO_CONTACTO WHERE PERSONA_ID = #{personaId}")
    @Results({
            @Result(property = "telefonoId", column = "TELEFONO_ID"),
            @Result(property = "telefono", column = "TELEFONO")
    })
    List<TelefonoContacto> listarTelefonos(Integer personaId);


    @Insert("INSERT INTO TELEFONO_CONTACTO (PERSONA_ID, TELEFONO) VALUES(#{persona.personaId},#{telefono.telefono})")
    @Options(useGeneratedKeys = true, keyProperty = "telefono.telefonoId", keyColumn = "TELEFONO_ID")
    void addTelefono(TelefonoContacto telefono, Persona persona);

    @Update("Update TELEFONO_CONTACTO SET TELEFONO=#{telefono.telefono} WHERE PERSONA_ID = #{personaId} AND TELEFONO_ID = #{telefono.telefonoId}")
    void updateTelefono(TelefonoContacto telefono, Integer personaId);

    @Delete("DELETE FROM TELEFONO_CONTACTO WHERE TELEFONO_ID = #{telefono.telefonoId} AND PERSONA_ID = #{personaId}")
    void deleteTelefono(TelefonoContacto telefono, Integer personaId);

}
