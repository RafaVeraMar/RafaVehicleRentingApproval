package com.babel.vehiclerentingapproval.services.impl;


import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase es la implementación de los métodos CRUD (Crear, Ver, Modificar y Eliminar) de Persona.
 *
 * @author enrique.munoz@babelgroup.com
 * @see PersonaService
 */
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
    /**
     * Agrega una nueva persona, realizando varias validaciones antes de insertar la persona en la base de datos.
     *
     * @param persona La persona de renting a agregar.
     * @return La persona agregada, incluyendo la información de las direcciones, teléfonos y productos contratados asociada.
     * @throws RequiredMissingFieldException Si alguno de las campos requeridos no se completan.
     * @throws WrongLenghtFieldException Si alguno de las campos sobrepasa la longitud.
     * @throws DniFoundException Si existe una persona con el mismo dni.
     * @see #validatePersonData(persona)
     * @see #validateNif(String)
     */
    @Override
    @Transactional
    public Persona addPersona (Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException, DniFoundException {

        this.validatePersonData(persona);
        this.validateNif(persona.getNif());

        persona=this.addPersonaDireccion(persona);

        Pais pais = this.paisMapper.getPais(persona.getNacionalidad().getIsoAlfa_2());

        persona.setNacionalidad(pais);

        this.personaMapper.insertPersona(persona);

        this.addTelefonos(persona);

        return persona;
    }
    /**
     * Agrega la lista de teléfonos a la base de datos.
     *
     * @param persona La persona con la lista de teléfonos a agregar.
     */
    private void addTelefonos(Persona persona) {
        for (TelefonoContacto telefonoContacto:persona.getTelefonos()) {
            this.telefonoMapper.addTelefono(telefonoContacto, persona);
        }
    }
    /**
     * Implementación de la validación por si existe persona por su ID
     *
     * @param idPersona Es el ID de la persona que se quiere buscar
     * @return Devuelve null si existe la persona y una excepción si no existe
     * @throws PersonaNotFoundException
     */
    @Override
    public Persona existPerson(int idPersona) throws RequestApiValidationException {
        if (idPersona < 0) {
            throw new PersonaNotFoundException(idPersona);
        }
        return null;
    }
    /**
     * Implementación de un método que devuelve los productos contratados por persona
     *
     * @param idPersona Es el ID de la persona que se le quiere buscar sus productos contratados
     * @return Devuelve la lista de productos contratados por la persona
     * @see #validatePersona(String)
     * @throws PersonaNotFoundException
     */
    @Override
    public List<ProductoContratado> viewPersonaProducto(int idPersona) throws PersonaNotFoundException {
        this.validatePersona(idPersona);
        return this.personaMapper.verProductosContratadosPersona(idPersona);
    }
    /**
     * Implementación de un método que actualiza el estado de los productos contratados por persona
     *
     * @param listaProductoPersona Es la lista de los productos contratados de una persona
     */
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

        if (persona.isDireccionDomicilioSameAsNotificacion()) {
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
    @Transactional
    public void modificarPersona(Persona persona) throws PersonaNotFoundException, DireccionNotFoundException {

        if (persona.isDireccionDomicilioSameAsNotificacion()) {
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
        }

        this.validatePersonaExistente(persona);

        //Actualizamos las direcciones anteriores
        direccionMapper.updateDireccion(persona.getDireccionDomicilio());
        direccionMapper.updateDireccion(persona.getDireccionNotificacion());

        //Insertamos el resto de cambios
        this.personaMapper.updatePersona(persona);

    }

    public void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        this.validateNombre(persona);
    }

    public void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        if ((persona.getNombre() == null) || persona.getNombre().isEmpty()) {
            throw new RequiredMissingFieldException();
        }
        if (persona.getNombre().length() > 50) {
            throw new WrongLenghtFieldException("nombre");
        }
    }

    public void validatePersona(int personaId) throws PersonaNotFoundException {
        if (!existePersona(personaId)){
            throw new PersonaNotFoundException();
        }
    }
    private void validatePersonaExistente(Persona persona) throws PersonaNotFoundException, DireccionNotFoundException {
        if (!existePersona(persona.getPersonaId())){
            throw new PersonaNotFoundException();
        }
        if(existeDireccion(persona.getDireccionDomicilio().getDireccionId())==false ||existeDireccion(persona.getDireccionNotificacion().getDireccionId())==false){ //Si no existe alguna direcicon
            throw new DireccionNotFoundException();
        }
    }

    public void validateNif(String nif) throws DniFoundException {
        if (existeNif(nif)){
            throw new DniFoundException();
        }
    }

    public boolean existePersona(int personaId){
        if(personaMapper.existePersona(personaId)==0){
            return false;
        }
        return true;
    }



    public boolean existeDireccion(int direccionId){
        if(this.direccionMapper.existeDireccion(direccionId)==0){
            return false;
        }else{
            return true;
        }
    }

    public boolean existeNif(String nif){
        if(this.personaMapper.existeNif(nif)!=0){
            return true;
        }
        return false;
    }
}
