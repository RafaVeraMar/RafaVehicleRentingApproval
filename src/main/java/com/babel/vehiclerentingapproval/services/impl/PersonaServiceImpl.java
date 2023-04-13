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

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

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

    public void updateEstadoPersonaProducto(List<ProductoContratado> listaProductoPersona){
        for(ProductoContratado productoContratado : listaProductoPersona){
            if(productoContratado.getFechaBaja() == null){
                productoContratado.setEstado(EstadoProductoContratado.VIGENTE);
            }else{
                productoContratado.setEstado(EstadoProductoContratado.VENCIDO);
            }
        }
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

    @Override
    public void modificarPersona(Persona persona) throws PersonaNotFoundException {

        this.validatePersonaExistente(persona);

        if (persona.isDireccionDomicilioSameAsNotificacion()){
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
            this.personaMapper.updatePersona(persona);
            modificarTelefono(persona);
        }else{
            this.personaMapper.updatePersona(persona);
            modificarTelefono(persona);

        }
    }

    public void modificarTelefono(Persona persona) {
        List<TelefonoContacto> telefonos = persona.getTelefonos();
        List<TelefonoContacto> telefonosAntiguos = telefonoMapper.listarTelefonos(persona.getPersonaId());
        int diferenciaJSONBBDD = telefonos.size()-telefonosAntiguos.size();
        
        //Comprobamos la diferencia de numeros

        if (diferenciaJSONBBDD<0){
            //Hay mas numeros en la BBDD que en el JSON nuevo. Necesitamos eliminar la diferencia de numeros y actualizar los existentes.
            for (int indice=0;indice<abs(diferenciaJSONBBDD);indice++) {
                this.telefonoMapper.deleteTelefono(persona.getTelefonos().get(indice), persona.getPersonaId() );
            }

            for (int indice=0;indice<telefonos.size();indice++) {
                this.telefonoMapper.updateTelefono(persona.getTelefonos().get(indice), persona.getPersonaId() );
            }

        } else if (diferenciaJSONBBDD>0) {
            //Hay mas numeros en el JSON que en la BBDD. Necesitamos añadir la diferencia de numeros y actuializar el resto.
            for (int indice=0;indice<diferenciaJSONBBDD;indice++) {
                this.telefonoMapper.addTelefono(persona.getTelefonos().get(indice), persona );
            }

            for (int indice=0;indice<telefonos.size();indice++) {
                this.telefonoMapper.updateTelefono(persona.getTelefonos().get(indice), persona.getPersonaId() );
            }

        }else {
            //Hay el mismo numero de telefonos en el JSON que el la BBDD. Hacemos un update.
            for (int i=0; i<telefonos.size();i++) {
                this.telefonoMapper.updateTelefono(persona.getTelefonos().get(i),persona.getPersonaId());
            }
        }
    }

    public void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        this.validateNombre(persona);
    }

    public void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        if ((persona.getNombre() == null) || persona.getNombre().isEmpty()) {
            throw new RequiredMissingFieldException();
        }
        if (persona.getNombre().length() > 50){
            throw new WrongLenghtFieldException();
        }
    }

    public void validatePersona(int personaId) throws PersonaNotFoundException {
        if (!existePersona(personaId)){
            throw new PersonaNotFoundException();
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
