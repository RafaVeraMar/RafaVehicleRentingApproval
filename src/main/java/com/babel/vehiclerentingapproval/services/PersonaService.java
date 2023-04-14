package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;

import java.util.List;
/**
 * Esta interfaz define métodos relacionados con la Persona y su tratamiento con la base de datos
 * @author andres.guijarro@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 * @author tomas.prados@babelgroup.com
 */

public interface PersonaService {
    /**
     * Consulta que inserta una persona en la base de datos
     *
     * @param persona La persona a insertar
     * @return la persona insertada
     */
    Persona addPersona(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException, DireccionNotFoundException, PersonaNotFoundException, DniFoundException;
    /**
     * Consulta que comprueba si una persona existe en la base de datos
     *
     * @param idPersona La persona a insertar
     * @return la persona a comprobar
     */
    Persona existPerson(int idPersona) throws RequestApiValidationException;

    /**
     * Consulta que devuelv
     * @param id
     * @return
     * @throws PersonaNotFoundException
     */
    List<ProductoContratado> viewPersonaProducto(int id) throws PersonaNotFoundException;
    void updateEstadoPersonaProducto(List<ProductoContratado> listaProductoPersona);

    public void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    public void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    public void validatePersona(int personaId) throws PersonaNotFoundException;

    public boolean existePersona(int personaId);

    public void modificarTelefono(Persona persona);
    void modificarPersona(Persona persona) throws PersonaNotFoundException, DireccionNotFoundException;

    boolean existeDireccion(int direccionId);
}
