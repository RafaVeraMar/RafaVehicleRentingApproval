package com.babel.vehiclerentingapproval.services.impl;


import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.DireccionMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.services.PersonaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private DireccionMapper direccionMapper;
    private PersonaMapper personaMapper;

    public PersonaServiceImpl(PersonaMapper personaMapper, DireccionMapper direccionMapper){
        this.personaMapper=personaMapper;
        this.direccionMapper = direccionMapper;
    }

    @Override
    @Transactional
    public Persona addPersona(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {

        this.validatePersonData(persona);

        persona=this.addPersonaDireccion(persona);
        this.personaMapper.insertPersona(persona);

        return persona;
    }

    @Override
    public Persona getPerson(int idPersona) throws RequestApiValidationException {
        if (idPersona<0){
            throw new RequestApiValidationException();
        }
        return null;
    }

    private Persona addPersonaDireccion(Persona persona){
        this.direccionMapper.insertDireccion(persona.getDireccionDomicilio());

        if (persona.isDireccionDomicilioSameAsNotificacion()){
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
        }else{
            this.direccionMapper.insertDireccion(persona.getDireccionNotificacion());
        }


        return persona;
    }

    @Override
    public void modificarPersona(Persona persona) throws PersonaNotFoundException {

        this.validatePersonaExistente(persona);

        if (persona.isDireccionDomicilioSameAsNotificacion()){
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
            this.personaMapper.updatePersona(persona);
        }else{
            this.personaMapper.updatePersona(persona);
        }
    }

    private void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        this.validateNombre(persona);
    }

    private void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        if ((persona.getNombre() == null) || persona.getNombre().isEmpty()) {
            throw new RequiredMissingFieldException();
        }
        if (persona.getNombre().length() > 50){
            throw new WrongLenghtFieldException();
        }
    }

    private void validatePersonaExistente(Persona persona) throws PersonaNotFoundException {
        if (!existePersona(persona.getPersonaId())){
            throw new PersonaNotFoundException();
        }
    }

    public boolean existePersona(int personaId){
        if(personaMapper.existePersona(personaId)==0){
            return false;
        }
        return true;
    }



}
