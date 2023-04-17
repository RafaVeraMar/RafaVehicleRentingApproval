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
 *Implementación del servicio de persona.
 *  Se realiza la lógica de persona para trasladar la información relacionada a personas desde el cliente a base de datos
 *  Se incluyen los mappers relacionados a persona:
 *      @see DireccionMapper
 *      @see PersonaMapper
 *      @see TelefonoMapper
 *      @see TipoViaMapper
 *      @see ProvinciaMapper
 *      @see PaisMapper
 * @author @author miguel.sdela@babelgroup.com, javier.serrano@babelgroup.com, ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com, javier.roldan@babelgroup.com
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
     * Añade una nueva solicitud de renting y devuelve un objeto ResponseEntity con la información
     * de la solicitud creada, incluido su ID.
     *<p>
     * El método maneja las siguientes excepciones: <br>
     * - RequiredMissingFieldException si falta algun campo por rellenar.<br>
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.<br>
     * - DniFoundException si se encuentra un dni ya existente previamente. <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @see PaisMapper
     * @see PersonaMapper
     * @see #validatePersona(int)
     * @see #validateNif(String)
     * @see #addPersonaDireccion(Persona)
     * @see #addTelefonos(Persona)
     * @param persona Persona con la informacion referente
     * @return se devuelve la persona por si se requieren mas operaciones a posteriori
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
     * Añade telefonos a persona.
     * @see TelefonoMapper
     * @param persona
     * @return void.
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
     * Se listan los productos asociados a una persona en concreto.
     *<p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra la persona asociada.<p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @see PersonaMapper
     * @see #validatePersona(int)
     * @see #updateEstadoPersonaProducto(List)
     * @param idPersona id de persona.
     * @return se devuelve la lita de productos asociada a la idPersona.
     */
    @Override
    public List<ProductoContratado> viewPersonaProducto(int idPersona) throws PersonaNotFoundException {
        this.validatePersona(idPersona);
        List<ProductoContratado> listaProductos = this.personaMapper.verProductosContratadosPersona(idPersona);
        this.updateEstadoPersonaProducto(listaProductos);
        return listaProductos;
    }

    /**
     * Se actualizan los estados de personas por producto.
     * @param listaProductoPersona productos asociados a una persona.
     * @return void.
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

    /**
     * Se añaden las direcciones de domicilio y notificacion a persona para un posterior update a la bbdd.
     *<p>
     * @see TipoViaMapper
     * @see ProvinciaMapper
     * @see DireccionMapper
     * @param persona Persona con la informacion referente
     * @return se devuelve la persona con las direcciones ya incluida, incluyendo los mappers del tipo de vía y provincia
     */
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

    /**
     * Modificación de una persona.
     *<p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra el idPersona asociado.<br>
     * - DireccionNotFoundException si se introducen datos no válidos en dirección.<br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @see DireccionMapper
     * @see PersonaMapper
     * @param persona Persona con la informacion referente
     * @return se devuelve la persona por si se requieren mas operaciones a posteriori
     */
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
        this.modificarTelefono(persona);
    }

    /**
     * Se modifican los registros de teléfono asociados a una persona.
     * @see TelefonoMapper
     * @param persona Persona con la informacion referente
     * @return void
     */
    @Transactional
    public void modificarTelefono(Persona persona) throws PersonaNotFoundException{

        if(existePersona(persona.getPersonaId())) {
            List<TelefonoContacto> telefonos = persona.getTelefonos();
            List<TelefonoContacto> telefonosAntiguos = telefonoMapper.listarTelefonos(persona.getPersonaId());

            for (int i = 0; i < telefonosAntiguos.size(); i++) {
                this.telefonoMapper.deleteTelefono(persona.getPersonaId(), telefonosAntiguos.get(i));
            }
            for (int i = 0; i < telefonos.size(); i++) {
                this.telefonoMapper.addTelefono(telefonos.get(i), persona);
            }
        }else{
            throw new PersonaNotFoundException();
        }
    }

    /**
     * Validacion de datos de persona (nombre)
     *<p>
     * El método maneja las siguientes excepciones: <br>
     * - RequiredMissingFieldException si falta algun campo por rellenar.<br>
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.<p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @see #validateNombre(Persona)
     * @param persona Persona con la informacion referente
     * @return void
     */
    public void validatePersonData(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        this.validateNombre(persona);
    }

    /**
     * Validacion del campo nombre de persona (nombre)
     *<p>
     * El método maneja las siguientes excepciones: <br>
     * - RequiredMissingFieldException si falta algun campo por rellenar.<br>
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.<p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @param persona Persona con la informacion referente
     * @return void
     */
    public void validateNombre(Persona persona) throws RequiredMissingFieldException, WrongLenghtFieldException {
        if ((persona.getNombre() == null) || persona.getNombre().isEmpty()) {
            throw new RequiredMissingFieldException();
        }
        if (persona.getNombre().length() > 50) {
            throw new WrongLenghtFieldException("nombre");
        }
    }
    /**
     * Validación de los datos de una persona
     *<p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra la ID de persona <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @param personaId Persona con la informacion referente
     * @return void
     */
    public void validatePersona(int personaId) throws PersonaNotFoundException {
        if (!existePersona(personaId)){
            throw new PersonaNotFoundException();
        }
    }

    /**
     * Validacion de la existencia de una persona <p>

     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra la ID de persona <br>
     * - DireccionNotFoundException si no se encuentra la ID de persona <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @see #existePersona(int)
     * @see #existeDireccion(int)
     * @param persona Persona con la informacion referente
     * @return void
     */
    private void validatePersonaExistente(Persona persona) throws PersonaNotFoundException, DireccionNotFoundException {
        if (!existePersona(persona.getPersonaId())){
            throw new PersonaNotFoundException();
        }
        if(existeDireccion(persona.getDireccionDomicilio().getDireccionId())==false ||existeDireccion(persona.getDireccionNotificacion().getDireccionId())==false){ //Si no existe alguna direcicon
            throw new DireccionNotFoundException();
        }
    }

    /**
     * Validacion del dni
     * El método maneja las siguientes excepciones: <br>
     * - DniFoundException si ya existe el dni proporcionado <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     * @see #existeNif(String)
     * @param nif dni de la persona
     * @return void
     */
    public void validateNif(String nif) throws DniFoundException {
        if (existeNif(nif)){
            throw new DniFoundException();
        }
    }

    /**
     * funcion booleana que comprueba el id de persona
     * @see PersonaMapper

     * @param personaId id que identifica a la persona
     * @return boolean
     */
    public boolean existePersona(int personaId){
        if(personaMapper.existePersona(personaId)==0){
            return false;
        }
        return true;
    }

    /**
     * funcion booleana que comprueba si existe id de dirección
     * @see DireccionMapper

     * @param direccionId id que identifica la direccion de persona
     * @return boolean
     */
    public boolean existeDireccion(int direccionId){
        if(this.direccionMapper.existeDireccion(direccionId)==0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * funcion booleana que comprueba si existe nif de persona
     * @see PersonaMapper
     * @param nif dni relacionado a persona
     * @return boolean
     */
    public boolean existeNif(String nif){
        if(this.personaMapper.existeNif(nif)!=0){
            return true;
        }
        return false;
    }
}
