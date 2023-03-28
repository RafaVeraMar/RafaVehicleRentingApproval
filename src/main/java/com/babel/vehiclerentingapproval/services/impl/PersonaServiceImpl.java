package com.babel.vehiclerentingapproval.services.impl;


import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.Pais;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.TelefonoContacto;
import com.babel.vehiclerentingapproval.models.TipoVia;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private DireccionMapper direccionMapper;
    private PersonaMapper personaMapper;
    private TelefonoMapper telefonoMapper;
    private TipoViaMapper tipoViaMapper;
    private ProvinciaMapper provinciaMapper;
    private PaisMapper paisMapper;

    public PersonaServiceImpl(DireccionMapper direccionMapper, PersonaMapper personaMapper, TelefonoMapper telefonoMapper, TipoViaMapper tipoViaMapper, ProvinciaMapper provinciaMapper, PaisMapper paisMapper) {
        this.direccionMapper = direccionMapper;
        this.personaMapper = personaMapper;
        this.telefonoMapper = telefonoMapper;
        this.tipoViaMapper = tipoViaMapper;
        this.provinciaMapper = provinciaMapper;
        this.paisMapper = paisMapper;
    }

    @Override
    @Transactional
    public Persona addPersona(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {

        this.validatePersonData(persona);

        persona=this.addPersonaDireccion(persona);
        this.addTelefonos(persona);
        this.personaMapper.insertPersona(persona);

        return persona;
    }

    private void addTelefonos(Persona persona) {
        for (TelefonoContacto telefonoContacto:persona.getTelefonos()) {
            this.telefonoMapper.addTelefono(telefonoContacto, persona);
        }
    }

    @Override
    public Persona getPerson(int idPersona) throws RequestApiValidationException {
        if (idPersona<0){
            throw new RequestApiValidationException();
        }
        return null;
    }

    private Persona addPersonaDireccion(Persona persona){
        this.paisMapper.insertPais(persona.getNacionalidad());
        this.tipoViaMapper.insertTipoVia(persona.getDireccionDomicilio().getTipoViaId());
        this.provinciaMapper.insertProvincia(persona.getDireccionDomicilio().getProvincia());
        this.direccionMapper.insertDireccion(persona.getDireccionDomicilio());

        if (persona.isDireccionDomicilioSameAsNotificacion()){
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
        }else{
            this.provinciaMapper.insertProvincia(persona.getDireccionNotificacion().getProvincia());
            this.tipoViaMapper.insertTipoVia(persona.getDireccionNotificacion().getTipoViaId());
            this.direccionMapper.insertDireccion(persona.getDireccionNotificacion());
        }


        return persona;
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



}
