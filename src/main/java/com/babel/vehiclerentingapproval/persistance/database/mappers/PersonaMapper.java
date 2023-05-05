package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.Persona;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import com.babel.vehiclerentingapproval.models.ProductoContratado;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Esta interfaz proporciona metodos para el manejo de la informacion de una persona en la base de datos
 *
 * @author andres.guijarro@babelgroup.com, rafael.vera@babelgroup.com, tomas.prados@babelgroup.com
 */
@Mapper
public interface PersonaMapper {
    /**
     * Consulta que inserta una persona en la base de datos
     * @see Persona
     * @param persona objeto Persona a insertar
     */
    @Insert("INSERT INTO PERSONA (NOMBRE, APELLIDO1, APELLIDO2, DIRECCION_DOMICILIO_ID, DIRECCION_NOTIFICACION_ID, NIF, FECHA_NACIMIENTO, NACIONALIDAD, EMAIL) VALUES (#{nombre}, #{apellido1}, #{apellido2}, #{direccionDomicilio.direccionId}, #{direccionNotificacion.direccionId}, #{nif}, #{fechaNacimiento, jdbcType=DATE}, #{nacionalidad.isoAlfa2},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "personaId", keyColumn = "PERSONA_ID")
    void insertPersona(Persona persona);
    /**
     * Consulta que devuelve el numero de personas existentes en la base de datos dado un ID
     *
     * @param personaId objeto Persona a insertar
     * @return numero de personas con ese ID en la base de datos
     */
    @Select("SELECT COUNT(PERSONA_ID) FROM PERSONA WHERE PERSONA_ID = #{personaId}")
    int existePersona(int personaId);
    /**
     * Consulta que devuelve el numero de NIFs que hay en la base de datos
     *
     * @param nif NIF a buscar
     * @return numero de NIFs
     */
    @Select("SELECT COUNT(NIF) FROM PERSONA WHERE NIF = #{nif,jdbcType=CHAR}")
    int existeNif(String nif);
    /**
     * Consulta que devuelve una lista de objetos ProductoContratado de una persona
     *
     * @param id ID de la persona
     * @return Lista de productos contratados de una persona
     * @see ProductoContratado
     */
    @Select("SELECT pc.PRODUCTO_CONTRATADO_ID, pc.PRODUCTO_ID, pc.ALIAS, pc.IMPORTE_NOMINAL, pc.FECHA_ALTA, pc.FECHA_BAJA " +
            "FROM PERSONA p INNER JOIN PRODUCTO_CONTRATADO_PERSONA pcp ON p.PERSONA_ID =pcp.PERSONA_ID " +
            "INNER JOIN PRODUCTO_CONTRATADO pc ON pc.PRODUCTO_CONTRATADO_ID = pcp.PRODUCTO_CONTRATADO_ID WHERE p.PERSONA_ID = #{id}")
    @Result(property = "idProductoContratado", column = "PRODUCTO_CONTRATADO_ID")
    @Result(property = "idProducto", column = "PRODUCTO_ID")
    @Result(property = "alias", column = "ALIAS")
    @Result(property = "importeNominal", column = "IMPORTE_NOMINAL")
    @Result(property = "fechaAlta", column = "FECHA_ALTA")
    @Result(property = "fechaBaja", column = "FECHA_BAJA")
    List<ProductoContratado> verProductosContratadosPersona(int id);

    /**
     * Consulta que actualiza una persona en la base de datos
     *
     * @param persona Persona a actualizar
     * @see Persona
     */
    @Update("UPDATE PERSONA SET NOMBRE=#{nombre}, APELLIDO1=#{apellido1}, APELLIDO2=#{apellido2},"+
            "DIRECCION_DOMICILIO_ID=#{direccionDomicilio.direccionId}, DIRECCION_NOTIFICACION_ID=#{direccionNotificacion.direccionId},"+
            "NIF=#{nif},FECHA_NACIMIENTO=#{fechaNacimiento,jdbcType=DATE},NACIONALIDAD=#{nacionalidad.isoAlfa2},"+
            "SCORING=#{scoring}, FECHA_SCORING=#{fechaScoring, jdbcType=DATE}, EMAIL=#{email} "+
            "WHERE PERSONA_ID=#{personaId}")
    void updatePersona(Persona persona);

    /**
     * Consulta que obtiene el email de una persona
     *
     * @param personaId ID de la persona de la que se desea obtener su email
     * @return email
     */
    @Select("SELECT EMAIL FROM PERSONA WHERE PERSONA_ID=#{personaId}")
    String getEmail(int personaId);

    @Select("SELECT PERSONA_ID FROM PERSONA WHERE EMAIL=#{email}")
    int getPersonaId(String email);

}
