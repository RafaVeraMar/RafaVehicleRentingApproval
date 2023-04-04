package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import org.apache.ibatis.annotations.*;


@Mapper
public interface PersonaMapper {

    @Insert("INSERT INTO PERSONA (NOMBRE, APELLIDO1, APELLIDO2, DIRECCION_DOMICILIO_ID, DIRECCION_NOTIFICACION_ID, NIF, FECHA_NACIMIENTO, NACIONALIDAD) VALUES " +
            "(#{nombre}, #{apellido1}, #{apellido2}, #{direccionDomicilio.direccionId}, #{direccionNotificacion.direccionId}, #{nif}, " +
            "#{fechaNacimiento, jdbcType=DATE}, #{nacionalidad})")
    @Options(useGeneratedKeys = true, keyProperty = "personaId", keyColumn = "PERSONA_ID")
    void insertPersona(Persona persona);



    @Select("SELECT COUNT(PERSONA_ID) FROM PERSONA WHERE PERSONA_ID = #{personaId}")
    int existePersona(int personaId);





    @Update("UPDATE PERSONA SET NOMBRE=#{nombre}, APELLIDO1=#{apellido1}, APELLIDO2=#{apellido2},"+
            "DIRECCION_DOMICILIO_ID=#{direccionDomicilio.direccionId}, DIRECCION_NOTIFICACION_ID=#{direccionNotificacion.direccionId},"+
            "NIF=#{nif},FECHA_NACIMIENTO=#{fechaNacimiento,jdbcType=DATE},NACIONALIDAD=#{nacionalidad},"+
            "SCORING=#{scoring}, FECHA_SCORING=#{fechaScoring, jdbcType=DATE}"+
            "WHERE PERSONA_ID=#{personaId}")
    void updatePersona(Persona persona);
}
