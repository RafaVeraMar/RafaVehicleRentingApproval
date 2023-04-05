package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RentaFoundException;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.springframework.stereotype.Service;

@Service
public class RentaServiceImpl implements RentaService {
    RentaMapper rentaMapper;




    PersonaService personaService;

    ProfesionService profesionService;

    public RentaServiceImpl(RentaMapper rentaMapper, PersonaService personaService, ProfesionService profesionService) {
        this.rentaMapper = rentaMapper;
        this.personaService = personaService;
        this.profesionService = profesionService;
    }

    @Override
    public Renta addRenta(Renta renta) throws ProfesionNotFoundException, PersonaNotFoundException, RentaFoundException {
        this.profesionService.validateProfesion(renta.getProfesion().getProfesionId());
        this.personaService.validatePersona(renta.getPersona().getPersonaId());
        this.validateRenta(renta.getRentaId());
        this.rentaMapper.addRenta(renta);
        return renta;
    }



    public void validateRenta(int rentaId) throws RentaFoundException {
        if(this.existeRenta(rentaId)){
            throw new RentaFoundException();
        }
    }

    public boolean existeRenta(int rentaId){
        if(this.rentaMapper.existeRenta(rentaId)!=0){
            return true;
        }
        return false;
    }

}
