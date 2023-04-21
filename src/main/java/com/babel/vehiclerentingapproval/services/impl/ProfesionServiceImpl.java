package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ProfesionMapper;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Esta clase define un método para comprobar la existencia de una profesion en la base de datos. Además define un metodo para validar la misma.
 *
 * @author andres.guijarro@babelgroup.com
 */
@Service
public class ProfesionServiceImpl implements ProfesionService {

    ProfesionMapper profesionMapper;

    public ProfesionServiceImpl (ProfesionMapper profesionMapper) {
        this.profesionMapper = profesionMapper;
    }

    /**
     * Consulta que devuelve si una profesion es valida o no en la base de datos
     *
     * @param profesionId id de la profesion a buscar
     * @return si es valida como profesion
     */
    public void validateProfesion (int profesionId) throws ProfesionNotFoundException {
        if (!existeProfesion(profesionId)) {
            throw new ProfesionNotFoundException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Consulta que devuelve si una profesion existe o no en la base de datos
     *
     * @param profesionId id de la profesion a buscar
     * @return si existe o no dicha profesion
     */

    public boolean existeProfesion (int profesionId) {
        return this.profesionMapper.existeProfesion(profesionId) != 0;
    }

}
