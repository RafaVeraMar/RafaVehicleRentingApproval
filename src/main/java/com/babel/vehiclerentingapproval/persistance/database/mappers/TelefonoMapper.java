package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.TelefonoContacto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TelefonoMapper {
    @Insert("INSERT INTO TELEFONO_CONTACTO (PERSONA_ID,TELEFONO_ID, TELEFONO) VALUES(#{persona.personaId},#{telefonoId},#{telefono})")
    @Options(useGeneratedKeys = true, keyProperty = "telefonoId", keyColumn = "TELEFONO_ID")
    void addTelefono(TelefonoContacto telefono, Persona persona);
}
