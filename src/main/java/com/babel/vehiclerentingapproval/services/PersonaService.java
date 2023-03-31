package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.ProductoContratado;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonaService {

    Persona addPersona(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    Persona getPerson(int idPersona) throws RequestApiValidationException;

    List<ProductoContratado> viewPersonaProducto(int id) throws PersonaNotFoundException;

    public void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    public void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    public void validatePersona(int personaId) throws PersonaNotFoundException;

    public boolean existePersona(int personaId);
}
