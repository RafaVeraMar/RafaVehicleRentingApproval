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
     * Consulta que devuelve una lista de productos que pertenecen a una persona
     * @param id el identificador de la persona
     * @return la lista de productos
     * @throws PersonaNotFoundException
     */
    List<ProductoContratado> viewPersonaProducto(int id) throws PersonaNotFoundException;
    /**
     * Consulta que actualiza el estado de los productos de la persona
     * @param listaProductoPersona lista de productos
     * @return la lista de productos
     */
    void updateEstadoPersonaProducto(List<ProductoContratado> listaProductoPersona);

    /**
     * Método que valida los datos de las personas para poder introducirlo o no
     * @param persona
     * @throws RequiredMissingFieldException
     * @throws WrongLenghtFieldException
     */
    public void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    /**
     * Metodo que comprueba si el nombre tiene un formato correcto
     * @param persona
     * @throws RequiredMissingFieldException
     * @throws WrongLenghtFieldException
     */

    public void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;
    /**
     * Metodo que comprueba si la persona un formato correcto
     * @param personaId
     * @throws RequiredMissingFieldException
     * @throws WrongLenghtFieldException
     */
    public void validatePersona(int personaId) throws PersonaNotFoundException;

    /**
     * metodo que comprueba si la persona existe o no en la base de datos
     * @param personaId
     * @return
     */
    public boolean existePersona(int personaId);

    /**
     * metodo que modifica el telefono de una persona
     * @param persona la persona a modificar
     */
    public void modificarTelefono(Persona persona) throws PersonaNotFoundException;

    /**
     * metodo que modifica una persona en la base de datos
     * @param persona  la persona a modificar
     * @throws PersonaNotFoundException
     * @throws DireccionNotFoundException
     */
    void modificarPersona(Persona persona) throws PersonaNotFoundException, DireccionNotFoundException;

    /**
     * Metodo que consulta la existencia de una direccion
     * @param direccionId la direccion a consultar
     * @return
     */
    boolean existeDireccion(int direccionId);

    public boolean existeNif(String nif);

    public void validateNif (String nif) throws DniFoundException;
}
