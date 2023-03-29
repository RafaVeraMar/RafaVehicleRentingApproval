package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonaMapper {

    @Insert("INSERT INTO PERSONA (NOMBRE, APELLIDO1, APELLIDO2, DIRECCION_DOMICILIO_ID, DIRECCION_NOTIFICACION_ID, NIF, FECHA_NACIMIENTO, NACIONALIDAD) VALUES (#{nombre}, #{apellido1}, #{apellido2}, #{direccionDomicilio.direccionId}, #{direccionNotificacion.direccionId}, #{nif}, #{fechaNacimiento, jdbcType=DATE}, #{nacionalidad.isoAlfa_2})")
    @Options(useGeneratedKeys = true, keyProperty = "personaId", keyColumn = "PERSONA_ID")
    void insertPersona(Persona persona);

    @Select("SELECT COUNT(PERSONA_ID) FROM PERSONA WHERE PERSONA_ID = #{personaId}")
    int existePersona(int personaId);

    @Select("SELECT PCP.PERSONA_ID, PC.PRODUCTO_CONTRATADO_ID, PC.FECHA_ALTA, PC.FECHA_BAJA FROM SCORING.PRODUCTO_CONTRATADO PC " +
    " JOIN SCORING.PRODUCTO_CONTRATADO_PERSONA PCP ON PCP.PRODUCTO_CONTRATADO_ID = PC.PRODUCTO_CONTRATADO_ID" +
    " JOIN SCORING.PERSONA P ON P.PERSONA_ID = PCP.PERSONA_ID WHERE P.NIF = #{nif}")
    List<ProductoContratado> verProductosContratados(String nif);
}
