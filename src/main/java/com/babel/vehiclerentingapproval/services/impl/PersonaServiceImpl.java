package com.babel.vehiclerentingapproval.services.impl;


import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Pais pais = this.paisMapper.getPais(persona.getNacionalidad().getIsoAlfa_2());
        persona.setNacionalidad(pais);
        this.personaMapper.insertPersona(persona);
        this.addTelefonos(persona);
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

    @Override
    public List<ProductoContratado> viewPersonaProducto(int idPersona) throws PersonaNotFoundException {
        this.validatePersona(idPersona);
        return this.personaMapper.verProductosContratadosPersona(idPersona);
    }

    private Persona addPersonaDireccion(Persona persona){

        TipoVia tipoVia=this.tipoViaMapper.getTipoVia(persona.getDireccionDomicilio().getTipoViaId().getTipoViaId());
        persona.getDireccionDomicilio().setTipoViaId(tipoVia);
        Provincia provincia = this.provinciaMapper.getProvincia(persona.getDireccionDomicilio().getProvincia().getCodProvincia());
        persona.getDireccionDomicilio().setProvinciaCod(provincia);
        this.direccionMapper.insertDireccion(persona.getDireccionDomicilio());

        if (persona.isDireccionDomicilioSameAsNotificacion()){
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
        }else{
            provincia = this.provinciaMapper.getProvincia(persona.getDireccionNotificacion().getProvincia().getCodProvincia());
            persona.getDireccionNotificacion().setProvinciaCod(provincia);
            tipoVia=this.tipoViaMapper.getTipoVia(persona.getDireccionNotificacion().getTipoViaId().getTipoViaId());
            persona.getDireccionNotificacion().setTipoViaId(tipoVia);
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

    public void validatePersona(int personaId) throws PersonaNotFoundException {
        if(existePersona(personaId)){
            throw new PersonaNotFoundException();
        }
    }

    public boolean existePersona(int personaId){
        if(this.personaMapper.existePersona(personaId)==0){
            return false;
        }
        return true;
    }
}
