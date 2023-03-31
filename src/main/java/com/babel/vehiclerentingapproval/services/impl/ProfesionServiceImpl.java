package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import org.springframework.stereotype.Service;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ProfesionMapper;

@Service
public class ProfesionServiceImpl implements ProfesionService {

    ProfesionMapper profesionMapper;

    public ProfesionServiceImpl(ProfesionMapper profesionMapper) {
        this.profesionMapper = profesionMapper;
    }

    public void validateProfesion(int profesionId) throws ProfesionNotFoundException {
        if(!existeProfesion(profesionId)){
            throw new ProfesionNotFoundException();
        }
    }



    public boolean existeProfesion(int profesionId){
        if(this.profesionMapper.existeProfesion(profesionId)!=0){
            return true;
        }
        return false;
    }

}
