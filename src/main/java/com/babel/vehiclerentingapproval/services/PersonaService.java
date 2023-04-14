package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;

import java.util.List;


public interface PersonaService {

    Persona addPersona(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException, DireccionNotFoundException, PersonaNotFoundException, DniFoundException;

    Persona existPerson(int idPersona) throws RequestApiValidationException;

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
