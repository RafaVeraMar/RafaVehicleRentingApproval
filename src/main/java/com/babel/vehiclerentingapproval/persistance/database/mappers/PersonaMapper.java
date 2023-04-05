package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonaMapper {

    @Insert("INSERT INTO PERSONA (NOMBRE, APELLIDO1, APELLIDO2, DIRECCION_DOMICILIO_ID, DIRECCION_NOTIFICACION_ID, NIF, FECHA_NACIMIENTO, NACIONALIDAD, EMAIL) VALUES (#{nombre}, #{apellido1}, #{apellido2}, #{direccionDomicilio.direccionId}, #{direccionNotificacion.direccionId}, #{nif}, #{fechaNacimiento, jdbcType=DATE}, #{nacionalidad.isoAlfa_2},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "personaId", keyColumn = "PERSONA_ID")
    void insertPersona(Persona persona);



    @Select("SELECT COUNT(PERSONA_ID) FROM PERSONA WHERE PERSONA_ID = #{personaId}")
    int existePersona(int personaId);

    @Select("SELECT pc.PRODUCTO_CONTRATADO_ID, pc.PRODUCTO_ID, pc.ALIAS, pc.IMPORTE_NOMINAL, pc.FECHA_ALTA, pc.FECHA_BAJA " +
            "FROM PERSONA p INNER JOIN PRODUCTO_CONTRATADO_PERSONA pcp ON p.PERSONA_ID =pcp.PERSONA_ID " +
            "INNER JOIN PRODUCTO_CONTRATADO pc ON pc.PRODUCTO_CONTRATADO_ID = pcp.PRODUCTO_CONTRATADO_ID WHERE p.PERSONA_ID = #{id}")
    @Results({
            @Result(property = "idProductoContratado", column = "PRODUCTO_CONTRATADO_ID"),
            @Result(property = "idProducto", column = "PRODUCTO_ID"),
            @Result(property = "alias", column = "ALIAS"),
            @Result(property = "importeNominal", column = "IMPORTE_NOMINAL"),
            @Result(property = "fechaAlta", column = "FECHA_ALTA"),
            @Result(property = "fechaBaja", column = "FECHA_BAJA")
    })
    List<ProductoContratado> verProductosContratadosPersona(int id);




    @Update("UPDATE PERSONA SET NOMBRE=#{nombre}, APELLIDO1=#{apellido1}, APELLIDO2=#{apellido2},"+
            "DIRECCION_DOMICILIO_ID=#{direccionDomicilio.direccionId}, DIRECCION_NOTIFICACION_ID=#{direccionNotificacion.direccionId},"+
            "NIF=#{nif},FECHA_NACIMIENTO=#{fechaNacimiento,jdbcType=DATE},NACIONALIDAD=#{nacionalidad.isoAlfa_2},"+
            "SCORING=#{scoring}, FECHA_SCORING=#{fechaScoring, jdbcType=DATE}, EMAIL={email}"+
            "WHERE PERSONA_ID=#{personaId}")
    void updatePersona(Persona persona);
}
