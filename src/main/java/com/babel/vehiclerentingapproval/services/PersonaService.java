package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.Persona;

public interface PersonaService {

    Persona addPersona(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException;

    Persona getPerson(int idPersona) throws RequestApiValidationException;


    void modificarPersona(Persona persona) throws PersonaNotFoundException;
}
