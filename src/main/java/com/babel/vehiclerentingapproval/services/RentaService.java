package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.models.Renta;

public interface RentaService {
    Renta addRenta(Renta renta) throws ProfesionNotFoundException, PersonaNotFoundException;
}
