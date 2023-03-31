package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ProfesionMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.springframework.stereotype.Service;

@Service
public class RentaServiceImpl implements RentaService {
    RentaMapper rentaMapper;
    ProfesionMapper profesionMapper;

    PersonaMapper personaMapper;

    public RentaServiceImpl(RentaMapper rentaMapper, ProfesionMapper profesionMapper, PersonaMapper personaMapper) {
        this.rentaMapper = rentaMapper;
        this.profesionMapper = profesionMapper;
        this.personaMapper = personaMapper;
    }

    @Override
    public Renta addRenta(Renta renta) throws ProfesionNotFoundException, PersonaNotFoundException {
        this.existeProfesion(renta.getProfesion().getProfesionId());
        this.existePersona(renta.getPersona().getPersonaId());
        this.rentaMapper.addRenta(renta);
        return renta;
    }

    private void existeProfesion(int profesionId) throws ProfesionNotFoundException {
         if(this.profesionMapper.existeProfesion(profesionId)==0){
             throw new ProfesionNotFoundException();
         }
    }

    private void existePersona(int personaId) throws PersonaNotFoundException{
        if(this.personaMapper.existePersona(personaId)==0){
            throw new PersonaNotFoundException();
        }
    }

}
