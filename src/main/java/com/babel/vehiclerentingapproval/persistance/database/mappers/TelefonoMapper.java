package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.TelefonoContacto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TelefonoMapper {
    @Insert("INSERT INTO TELEFONO_CONTACTO (PERSONA_ID, TELEFONO) VALUES(#{persona.personaId},#{telefono.telefono})")
    @Options(useGeneratedKeys = true, keyProperty = "telefono.telefonoId", keyColumn = "TELEFONO_ID")
    void addTelefono(TelefonoContacto telefono, Persona persona);
}
