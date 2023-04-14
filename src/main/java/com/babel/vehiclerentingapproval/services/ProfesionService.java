package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;

/**
 * Esta interfaz define un método para comprobar la existencia de una profesion en la base de datos. Además define un metodo para validar la misma
 * @author andres.guijarro@babelgroup.com
 */
public interface ProfesionService {
    /**
     * Consulta que devuelve si una profesion existe o no en la base de datos
     *
     * @param profesionId id de la profesion a buscar
     * @return si existe o no dicha profesion
     */
    public boolean existeProfesion(int profesionId);
    /**
     * Consulta que devuelve si una profesion es valida o no en la base de datos
     *
     * @param profesionId id de la profesion a buscar
     * @return si es valida como profesion
     */
    public void validateProfesion(int profesionId) throws ProfesionNotFoundException;
}
