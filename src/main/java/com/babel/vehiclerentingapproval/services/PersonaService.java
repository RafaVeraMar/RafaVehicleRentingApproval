package com.babel.vehiclerentingapproval.services;

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

    List<ProductoContratado> viewPersonaProducto(int id);
}
