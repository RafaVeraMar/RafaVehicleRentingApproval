package com.babel.vehiclerentingapproval.services.impl;


import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PersonaService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio de persona.
 * Se realiza la lógica de persona para trasladar la información relacionada a personas desde el cliente a base de datos
 * Se incluyen los mappers relacionados a persona:
 *
 * @author @author miguel.sdela@babelgroup.com, javier.serrano@babelgroup.com, ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com, javier.roldan@babelgroup.com
 * @see DireccionMapper
 * @see PersonaMapper
 * @see TelefonoMapper
 * @see TipoViaMapper
 * @see ProvinciaMapper
 * @see PaisMapper
 */
@Log4j2
@Service
public class PersonaServiceImpl implements PersonaService {

    private DireccionMapper direccionMapper;
    private PersonaMapper personaMapper;
    private TelefonoMapper telefonoMapper;
    private TipoViaMapper tipoViaMapper;
    private ProvinciaMapper provinciaMapper;
    private PaisMapper paisMapper;

    public PersonaServiceImpl (DireccionMapper direccionMapper, PersonaMapper personaMapper, TelefonoMapper telefonoMapper, TipoViaMapper tipoViaMapper, ProvinciaMapper provinciaMapper, PaisMapper paisMapper) {
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
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - RequiredMissingFieldException si falta algun campo por rellenar.<br>
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.<br>
     * - DniFoundException si se encuentra un dni ya existente previamente. <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param persona Persona con la informacion referente
     * @return se devuelve la persona por si se requieren mas operaciones a posteriori
     * @see PaisMapper
     * @see PersonaMapper
     * @see #validatePersona(int)
     * @see #validateNif(String)
     * @see #addPersonaDireccion(Persona)
     * @see #addTelefonos(Persona)
     */
    @Override
    @Transactional
    public Persona addPersona (Persona persona) {
        log.info("Procesando la adicción de una persona");
        this.validatePersonData(persona);
        this.validateNif(persona.getNif());

        this.addPersonaDireccion(persona);

        var pais = this.paisMapper.getPais(persona.getNacionalidad().getIsoAlfa2());

        persona.setNacionalidad(pais);
        this.personaMapper.insertPersona(persona);
        this.addTelefonos(persona);
        log.info("Proceso de de adicción de una persona finalizado");
        return persona;
    }

    /**
     * Añade telefonos a persona.
     *
     * @param persona
     * @return void.
     * @see TelefonoMapper
     */
    private void addTelefonos (Persona persona) {
        log.info("Procesando la adicción de los teléfonos de una persona");
        for (TelefonoContacto telefonoContacto : persona.getTelefonos()) {
            this.telefonoMapper.addTelefono(telefonoContacto, persona);
        }
        log.info("Proceso de adicción de los teléfonos de una persona finalizado");
    }

    /**
     * Implementación de la validación por si existe persona por su ID
     *
     * @param idPersona Es el ID de la persona que se quiere buscar
     * @return Devuelve null si existe la persona y una excepción si no existe
     * @throws PersonaNotFoundException
     */
    @Override
    public Persona invalidPersonId (int idPersona) {
        log.info("Procesando la validación de existencia de una persona");
        if (idPersona < 0) {
            throw new PersonaNotFoundException(idPersona, HttpStatus.NOT_FOUND);
        }
        log.info("Proceso de validación de existencia de una persona finalizado");
        return null;
    }

    /**
     * Se listan los productos asociados a una persona en concreto.
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra la persona asociada.<p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param idPersona id de persona.
     * @return se devuelve la lita de productos asociada a la idPersona.
     * @see PersonaMapper
     * @see #validatePersona(int)
     * @see #updateEstadoPersonaProducto(List)
     */
    @Override
    public List<ProductoContratado> viewPersonaProducto (int idPersona) {
        log.info("Procesando la visualización productos contratados por persona");
        this.validatePersona(idPersona);
        List<ProductoContratado> listaProductos = this.personaMapper.verProductosContratadosPersona(idPersona);
        this.updateEstadoPersonaProducto(listaProductos);
        log.info("Proceso de visualización productos contratados por persona finalizado");
        return listaProductos;
    }

    /**
     * Se actualizan los estados de personas por producto.
     *
     * @param listaProductoPersona productos asociados a una persona.
     * @return void.
     */
    public void updateEstadoPersonaProducto (List<ProductoContratado> listaProductoPersona) {
        log.info("Procesando la actualización de los estados de los productos contratados de una persona");
        for (ProductoContratado productoContratado : listaProductoPersona) {
            if (productoContratado.getFechaBaja() == null) {
                productoContratado.setEstado(EstadoProductoContratado.VIGENTE);
            } else {
                productoContratado.setEstado(EstadoProductoContratado.VENCIDO);
            }
        }
        log.info("Proceso de actualización de los estados de los productos contratados de una persona finalizado");
    }

    /**
     * Se añaden las direcciones de domicilio y notificacion a persona para un posterior update a la bbdd.
     * <p>
     *
     * @param persona Persona con la informacion referente
     * @return se devuelve la persona con las direcciones ya incluida, incluyendo los mappers del tipo de vía y provincia
     * @see TipoViaMapper
     * @see ProvinciaMapper
     * @see DireccionMapper
     */
    private Persona addPersonaDireccion (Persona persona) {
        log.info("Procesando la adicción de la dirección de una persona");
        var tipoVia = this.tipoViaMapper.getTipoVia(persona.getDireccionDomicilio().getTipoViaId().getTipoViaId());
        persona.getDireccionDomicilio().setTipoViaId(tipoVia);
        var provincia = this.provinciaMapper.getProvincia(persona.getDireccionDomicilio().getProvincia().getCodProvincia());
        persona.getDireccionDomicilio().setProvincia(provincia);
        this.direccionMapper.insertDireccion(persona.getDireccionDomicilio());
        if (persona.isDireccionDomicilioSameAsNotificacion()) {
            persona.setDireccionNotificacion(persona.getDireccionDomicilio());
        } else {
            provincia = this.provinciaMapper.getProvincia(persona.getDireccionNotificacion().getProvincia().getCodProvincia());
            persona.getDireccionNotificacion().setProvincia(provincia);
            tipoVia = this.tipoViaMapper.getTipoVia(persona.getDireccionNotificacion().getTipoViaId().getTipoViaId());
            persona.getDireccionNotificacion().setTipoViaId(tipoVia);
            this.direccionMapper.insertDireccion(persona.getDireccionNotificacion());
        }
        log.info("Proceso de adicción de la dirección de una persona finalizado");
        return persona;
    }

    /**
     * Modificación de una persona.
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra el idPersona asociado.<br>
     * - DireccionNotFoundException si se introducen datos no válidos en dirección.<br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param persona Persona con la informacion referente
     * @return se devuelve la persona por si se requieren mas operaciones a posteriori
     * @see DireccionMapper
     * @see PersonaMapper
     */
    @Override
    @Transactional
    public void modificarPersona (Persona persona) {
        log.info("Procesando la modificación de los datos de una persona");
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
        log.info("Proceso de modificación de los datos de una persona finalizado");
    }

    /**
     * Se modifican los registros de teléfono asociados a una persona.
     *
     * @param persona Persona con la informacion referente
     * @return void
     * @see TelefonoMapper
     */
    @Transactional
    public void modificarTelefono (Persona persona){
        log.info("Procesando la modificación de los teléfonos de una persona");
        if (existePersona(persona.getPersonaId())) {
            List<TelefonoContacto> telefonos = persona.getTelefonos();
            List<TelefonoContacto> telefonosAntiguos = telefonoMapper.listarTelefonos(persona.getPersonaId());

            for (var i = 0; i < telefonosAntiguos.size(); i++) {
                this.telefonoMapper.deleteTelefono(persona.getPersonaId(), telefonosAntiguos.get(i));
            }
            for (var i = 0; i < telefonos.size(); i++) {
                this.telefonoMapper.addTelefono(telefonos.get(i), persona);
            }
        } else {
            throw new PersonaNotFoundException();
        }
        log.info("Proceso de modificación de los teléfonos de una persona finalizado");
    }

    /**
     * Validacion de datos de persona (nombre)
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - RequiredMissingFieldException si falta algun campo por rellenar.<br>
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.<p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param persona Persona con la informacion referente
     * @return void
     * @see #validateNombre(Persona)
     */
    public void validatePersonData (Persona persona){
        log.info("Procesando la validación de los datos de una persona");
        this.validateNombre(persona);
        log.info("Proceso de validación de los datos de una persona finalizado");
    }

    /**
     * Validacion del campo nombre de persona (nombre)
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - RequiredMissingFieldException si falta algun campo por rellenar.<br>
     * - WrongLenghtFieldException si los datos de entrada sobrepasan la longitud máxima.<p>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param persona Persona con la informacion referente
     * @return void
     */
    public void validateNombre (Persona persona){
        log.info("Procesando la validación del nombre de una persona");
        if ((persona.getNombre() == null) || persona.getNombre().isEmpty()) {
            throw new RequiredMissingFieldException(HttpStatus.BAD_REQUEST);
        }
        if (persona.getNombre().length() > 50) {
            throw new WrongLenghtFieldException("nombre", HttpStatus.BAD_REQUEST);
        }
        log.info("Proceso de validación del nombre de una persona finalizado");
    }

    /**
     * Validación de los datos de una persona
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra la ID de persona <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param personaId Persona con la informacion referente
     * @return void
     */
    public void validatePersona (int personaId) {
        log.info("Procesando la validación de los datos de una persona");
        if (!existePersona(personaId)) {
            throw new PersonaNotFoundException();
        }
        log.info("Proceso de validación de los datos de una persona finalizado");
    }

    /**
     * Validacion de la existencia de una persona <p>
     * <p>
     * El método maneja las siguientes excepciones: <br>
     * - PersonaNotFoundException si no se encuentra la ID de persona <br>
     * - DireccionNotFoundException si no se encuentra la ID de persona <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param persona Persona con la informacion referente
     * @return void
     * @see #existePersona(int)
     * @see #existeDireccion(int)
     */
    private void validatePersonaExistente (Persona persona){
        log.info("Procesando la validación de la existencia de una persona");
        if (!existePersona(persona.getPersonaId())) {
            throw new PersonaNotFoundException();
        }
        if (!existeDireccion(persona.getDireccionDomicilio().getDireccionId()) || !existeDireccion(persona.getDireccionNotificacion().getDireccionId())) { //Si no existe alguna direcicon
            throw new DireccionNotFoundException(HttpStatus.NOT_FOUND);
        }
        log.info("Proceso de validación de la existencia de una persona finalizado");
    }

    /**
     * Validacion del dni
     * El método maneja las siguientes excepciones: <br>
     * - DniFoundException si ya existe el dni proporcionado <br>
     * En caso de cualquier otra excepción, se devuelve un error interno.
     *
     * @param nif dni de la persona
     * @return void
     * @see #existeNif(String)
     */
    public void validateNif (String nif)  {
        log.info("Procesando la validación del dni de una persona");
        if (existeNif(nif)) {
            throw new DniFoundException(HttpStatus.NOT_FOUND);
        }
        log.info("Proceso de validación del dni de una persona finalizado");
    }

    /**
     * funcion booleana que comprueba el id de persona
     *
     * @param personaId id que identifica a la persona
     * @return boolean
     * @see PersonaMapper
     */
    @Timed(value = "validacionIdPersona.time", description = "Time taken to return solicitudRenting")
    public boolean existePersona (int personaId) {
        log.info("Procesando la validación del id de una persona");
        return personaMapper.existePersona(personaId) != 0;
    }

    /**
     * funcion booleana que comprueba si existe id de dirección
     *
     * @param direccionId id que identifica la direccion de persona
     * @return boolean
     * @see DireccionMapper
     */
    public boolean existeDireccion (int direccionId) {
        log.info("Procesando la validación del id de la dirección de una persona");
        return this.direccionMapper.existeDireccion(direccionId) != 0;
    }

    /**
     * funcion booleana que comprueba si existe nif de persona
     *
     * @param nif dni relacionado a persona
     * @return boolean
     * @see PersonaMapper
     */
    public boolean existeNif (String nif) {
        log.info("Procesando la comprobación de existencia del nif de una persona");
        return this.personaMapper.existeNif(nif) != 0;
    }
}
