package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;

public interface ProfesionService {
    public boolean existeProfesion(int profesionId);
    public void validateProfesion(int profesionId) throws ProfesionNotFoundException;
}
