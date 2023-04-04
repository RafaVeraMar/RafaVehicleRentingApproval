package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;


@Mapper
public interface PersonaMapper {

    @Insert("INSERT INTO PERSONA (NOMBRE, APELLIDO1, APELLIDO2, DIRECCION_DOMICILIO_ID, DIRECCION_NOTIFICACION_ID, NIF, FECHA_NACIMIENTO, NACIONALIDAD) VALUES (#{nombre}, #{apellido1}, #{apellido2}, #{direccionDomicilio.direccionId}, #{direccionNotificacion.direccionId}, #{nif}, #{fechaNacimiento, jdbcType=DATE}, #{nacionalidad})")
    @Options(useGeneratedKeys = true, keyProperty = "personaId", keyColumn = "PERSONA_ID")
    void insertPersona(Persona persona);

    @Select("SELECT COUNT(PERSONA_ID) FROM PERSONA WHERE PERSONA_ID = #{personaId}")
    int existePersona(int personaId);

    @Select("SELECT NACIONALIDAD FROM PERSONA WHERE PERSONA_ID = #{personaId}")
    String getNationality(SolicitudRenting solicitudRenting);

@Select("SELECT p.PERSONA_ID, pcp.PRODUCTO_CONTRATADO_ID, sr.COD_RESOLUCION FROM Persona LEFT JOIN PRODUCTO_CONTRATADO_PERSONA pcp ON p.persona_ID = pcp.persona_ID AND PRODUCTO_CONTRATADO_ID IS NULL LEFT JOIN SOLICITUD_RENTING sr ON p.persona_ID = sr.PERSONA_ID AND sr.COD_RESOLUCION != 'AG' WHERE PERSONA_ID = #{personaId}")
boolean validatefindPersonasByCodResultado(SolicitudRenting solicitudRenting);
}
