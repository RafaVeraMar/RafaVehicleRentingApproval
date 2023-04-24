package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.EmailService;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.math.BigInteger;
import java.util.List;

/**
 * Esta clase es la implementación de los métodos CRUD (Crear, Ver, Modificar y Cancelar) de las Solicitudes de Renting.
 *
 * @author miguel.sdela@babelgroup.com, javier.serrano@babelgroup.com, ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com, javier.roldan@babelgroup.com
 * @see SolicitudRentingService
 */

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private final SolicitudRentingMapper solicitudRentingMapper;
    private final TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    private final PersonaService personaService;
    private final CodigoResolucionValidator codigoResolucionValidator;

    private final PersonaMapper personaMapper;
    private final EmailService emailService;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper, TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper, PersonaService personaService, CodigoResolucionValidator codigoResolucionValidator, PersonaMapper personaMapper, EmailService emailService) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
        this.personaService = personaService;
        this.codigoResolucionValidator = codigoResolucionValidator;
        this.personaMapper = personaMapper;
        this.emailService = emailService;

    }

    /**
     * Agrega una nueva solicitud de renting, realizando varias validaciones antes de insertar la solicitud en la base de datos.
     *
     * @param solicitudRenting La solicitud de renting a agregar.
     * @return La solicitud de renting agregada, incluyendo la información de la persona asociada.
     * @throws RequestApiValidationException Si alguna de las validaciones no se cumple.
     * @see #validatePersona(int)
     * @see #validateNumVehiculos(SolicitudRenting)
     * @see #validateInversion(SolicitudRenting)
     * @see #validateCuota(SolicitudRenting)
     * @see #validatePlazo(SolicitudRenting)
     * @see #validateFecha(SolicitudRenting)
     */
    @Override
    public int addSolicitudRenting (SolicitudRenting solicitudRenting) {
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        validateNumVehiculos(solicitudRenting);
        validateInversion(solicitudRenting);
        validateCuota(solicitudRenting);
        validatePlazo(solicitudRenting);
        validateFecha(solicitudRenting);
        solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
        solicitudRenting.setPersona(personaService.invalidPersonId(solicitudRenting.getPersona().getPersonaId()));
        return solicitudRenting.getSolicitudId();
    }

    /**
     * Comprueba si existe una persona con el identificador proporcionado.
     *
     * @param idPersona el identificador de la persona a verificar
     * @throws PersonaNotFoundException si no se encuentra una persona con el identificador proporcionado
     */
    private void existIdPersona (int idPersona) {
        if (!personaService.existePersona(idPersona)) {
            throw new PersonaNotFoundException(idPersona, HttpStatus.NOT_FOUND);

        }
    }

    /**
     * Devuelve el estado de una solicitud de renting a partir de su id
     *
     * @param idSolicitud el ID de la solicitud a consultar
     * @return el estado de la solicitud como una cadena de caracteres
     * @throws RequestApiValidationException si la id de la solicitud no existe, el codigo de resolucion es nulo, o no es valido
     */
    @Override
    public String verEstadoSolicitud(String idSolicitud) throws RequestApiValidationException {
        int id;
        try{
            id = Integer.parseInt(idSolicitud);
        }
        catch (NumberFormatException e){
            throw new IdIncorrectFormatException(HttpStatus.BAD_REQUEST);
        }
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(id);

        validarCodResolucionExiste(codigoExiste);

        TipoResultadoSolicitud resultadoSolicitud = this.tipoResultadoSolicitudMapper.getResultadoSolicitud(id);
        this.validarCodigoResolucion(resultadoSolicitud.getCodResultado());

        return resultadoSolicitud.getDescripcion();


    }

    /**
     * Comprueba que el codigo de resolucion de la solicitud existe.
     *
     * @param codResolucion Valor encontrado al hacer la consulta en la base de datos
     * @throws EstadoSolicitudNotFoundException si el codigo de resolución es nulo o el id de la solicitud no existe
     */
    private void validarCodResolucionExiste(int codResolucion) throws EstadoSolicitudNotFoundException {

        if (codResolucion == 0) { //idSolicitud or codResolucion null
            throw new EstadoSolicitudNotFoundException(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Método que comprueba si el codigo de la resolución es válido
     *
     * @param codResolucion el codigo de la resolucion
     * @throws EstadoSolicitudInvalidException si el codigo de resolucion no es valido
     * @see CodigoResolucionValidatorImpl
     */
    private void validarCodigoResolucion(String codResolucion) throws EstadoSolicitudInvalidException {
        this.codigoResolucionValidator.validarCodResolucion(codResolucion);

    }

    /**
     * Implementación del método que busca una solicitud por su ID
     *
     * @param id Es el ID de la solicitud que se quiere buscar
     * @return Devuelve un objeto Solicitud Renting
     * @throws RequestApiValidationException
     */

    public SolicitudRenting getSolicitudById(int id) throws RequestApiValidationException {
        var solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        validateSolicitudRenting(solicitudRenting);
        return solicitudRenting;
    }

    /**
     * Modifica únicamete el estado de una solicitud de renting, se comprueba a través de la base de datos que el nuevo estado sea uno de los valores posible.
     *
     * @param solicitudId ID de solicitud de renting.
     * @param nuevoEstado Nuevo estado de solicitud rentinh por validar.
     * @return void (llamada a base de datos (mapper) para modificar el estado.
     * @throws SolicitudRentingNotFoundException cuando no exista el ID de la solicitud.
     * @throws EstadoSolicitudNotFoundException  cuando el estado de la solicitud no sea uno de los valores válidos posibles.
     * @see #getListaEstados()
     * @see SolicitudRentingService
     * @see SolicitudRentingMapper
     */

    @Transactional
    @Override
    public void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws RequestApiValidationException, MessagingException{

        List<String> posiblesEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        int existeEstado = this.solicitudRentingMapper.existeSolicitud(solicitudId);
        SolicitudRenting solicitud = this.solicitudRentingMapper.getSolicitudByID(solicitudId);

        if (!posiblesEstados.contains(nuevoEstado.getCodResultado())) {
            throw new EstadoSolicitudNotFoundException(HttpStatus.NOT_FOUND);
        }
        if (existeEstado == 0) {
            throw new SolicitudRentingNotFoundException(HttpStatus.NOT_FOUND);
        }

        String email = this.personaMapper.getEmail(solicitud.getPersona().getPersonaId());
        if (email == null || email.indexOf('@') == -1) {
            throw new FailedSendingEmail(HttpStatus.BAD_REQUEST,"Failed");
        }
        var estadoSolicitud = this.tipoResultadoSolicitudMapper.getEstadoSolicitud(solicitudId);
        emailService.sendMail("Su solicitud se encuentra: " + estadoSolicitud, email, "Cambios en tu solicitud");
        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId, nuevoEstado);
    }

    
    /**
     * Modifica únicamete el estado de una solicitud de renting, se comprueba a través de la base de datos que el nuevo estado sea uno de los valores posible.
     *
     * @return List<String> (llamada a base de datos (mapper) para recoger posibles estados.
     */
    @Override
    public List<String> getListaEstados() {
        return this.tipoResultadoSolicitudMapper.getListaEstados();
    }

    /**
     * Calcula la cantidad de dígitos en un objeto BigInteger.
     *
     * @param number el objeto BigInteger cuya cantidad de dígitos se desea calcular
     * @return la cantidad de dígitos en el objeto BigInteger; si el objeto BigInteger es nulo, devuelve 0
     */
    private int lenghtNumber(BigInteger number) {
        if (number != null) {
            var numeroString = number.toString();
            return numeroString.length();
        }
        return 0;
    }

    /**
     * Valida si existe una persona con el ID proporcionado.
     *
     * @param idPersona El identificador de la persona a validar.
     * @throws PersonaNotFoundException Si no se encuentra una persona con el ID especificado.
     * @see #existIdPersona(int)
     */
    private void validatePersona (int idPersona) {
        existIdPersona(idPersona);
    }

    /**
     * Valida el número de vehículos en una solicitud de renting.
     * Verifica si el valor de 'numVehiculos' cumple con las siguientes condiciones:
     * 1. No tiene más de 38 dígitos.
     * 2. No es nulo o vacío.
     * 3. No es negativo o igual a cero.
     *
     * @param solicitudRenting el objeto SolicitudRenting cuyo campo 'numVehiculos' se va a validar
     * @throws WrongLenghtFieldException      si el valor de 'numVehiculos' tiene más de 38 dígitos
     * @throws InputIsNullOrIsEmpty           si el valor de 'numVehiculos' es nulo o vacío
     * @throws InputIsNegativeOrZeroException si el valor de 'numVehiculos' es negativo o igual a cero
     * @see #lenghtNumber(BigInteger)
     */
    private void validateNumVehiculos (SolicitudRenting solicitudRenting) {
        if (lenghtNumber(solicitudRenting.getNumVehiculos()) > 38) {
            throw new WrongLenghtFieldException("numVehiculos", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getNumVehiculos() == null) {
            throw new InputIsNullOrIsEmpty("numVehivulos", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getNumVehiculos().signum() == -1 || solicitudRenting.getNumVehiculos().signum() == 0) {
            throw new InputIsNegativeOrZeroException("numVehiculos", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Valida el campo 'inversion' en una solicitud de renting.
     * Verifica si el valor de 'inversion' cumple con las siguientes condiciones:
     * 1. No es nulo o vacío.
     * 2. No es negativo o igual a cero.
     *
     * @param solicitudRenting el objeto SolicitudRenting cuyo campo 'inversion' se va a validar
     * @throws InputIsNullOrIsEmpty           si el valor de 'inversion' es nulo o vacío
     * @throws InputIsNegativeOrZeroException si el valor de 'inversion' es negativo o igual a cero
     */
    private void validateInversion (SolicitudRenting solicitudRenting) {
        if (solicitudRenting.getInversion() == null) {
            throw new InputIsNullOrIsEmpty("inversion", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getInversion() < 1) {
            throw new InputIsNegativeOrZeroException("inversion", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Valida el campo 'cuota' en una solicitud de renting.
     * Verifica si el valor de 'cuota' cumple con las siguientes condiciones:
     * 1. No es nulo o vacío.
     * 2. No es negativo o igual a cero.
     *
     * @param solicitudRenting el objeto SolicitudRenting cuyo campo 'cuota' se va a validar
     * @throws InputIsNullOrIsEmpty           si el valor de 'cuota' es nulo o vacío
     * @throws InputIsNegativeOrZeroException si el valor de 'cuota' es negativo o igual a cero
     */
    private void validateCuota (SolicitudRenting solicitudRenting) {
        if (solicitudRenting.getCuota() == null) {
            throw new InputIsNullOrIsEmpty("cuota", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getCuota() < 1) {
            throw new InputIsNegativeOrZeroException("cuota", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Valida el campo 'plazo' en una solicitud de renting.
     * Verifica si el valor de 'plazo' cumple con las siguientes condiciones:
     * 1. No tiene más de 38 dígitos.
     * 2. No es negativo o igual a cero.
     * <p>
     * Si el valor de 'plazo' es nulo, no se lanza ninguna excepción.
     *
     * @param solicitudRenting el objeto SolicitudRenting cuyo campo 'plazo' se va a validar
     * @throws WrongLenghtFieldException      si el valor de 'plazo' tiene más de 38 dígitos
     * @throws InputIsNegativeOrZeroException si el valor de 'plazo' es negativo o igual a cero
     */
    private void validatePlazo (SolicitudRenting solicitudRenting) {
        if (solicitudRenting.getPlazo() != null) {
            if (lenghtNumber(solicitudRenting.getPlazo()) > 38) {
                throw new WrongLenghtFieldException("Plazo", HttpStatus.BAD_REQUEST);
            }
            if (solicitudRenting.getPlazo().signum() == -1 || solicitudRenting.getPlazo().signum() == 0) {
                throw new InputIsNegativeOrZeroException("plazo", HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * Valida las fechas 'fechaInicioVigor' y 'fechaResolucion' en una solicitud de renting.
     * Verifica si la 'fechaInicioVigor' es posterior a la 'fechaResolucion'.
     * <p>
     * Si alguna de las fechas es nula, no se lanza ninguna excepción.
     *
     * @param solicitudRenting el objeto SolicitudRenting cuyas fechas se van a validar
     * @throws DateIsBeforeException si 'fechaInicioVigor' es anterior a 'fechaResolucion'
     */
    private void validateFecha (SolicitudRenting solicitudRenting) {
        if ((solicitudRenting.getFechaInicioVigor() != null && solicitudRenting.getFechaResolucion() != null)
                && (solicitudRenting.getFechaInicioVigor().before(solicitudRenting.getFechaResolucion()))) {
            throw new DateIsBeforeException("fechaInicioVigo", "fechaResolucion",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Servicio que se encarga de cancelar la solicitud de renting asociada al id que se le pasa como parametro, solicitud que encontramos gracias al uso del metodo getSolicitudById
     *
     * @param id de la solicitud de renting
     * @throws SolicitudRentingNotFoundException que recoge la excepcion cuando la solicitud de renting es nula, si la solicitud no es nula la devuelve cancelada
     */
    public void cancelarSolicitud(int id) throws RequestApiValidationException {
        var solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        validateSolicitudRenting(solicitudRenting);
        solicitudRentingMapper.cancelarSolicitud(solicitudRenting);
    }

    /**
     * Validacion de si una Solicitud de Renting es nula o no
     *
     * @param solicitudRenting solicitud a comprobar si es nula
     * @throws SolicitudRentingNotFoundException
     */
    public void validateSolicitudRenting(SolicitudRenting solicitudRenting) throws SolicitudRentingNotFoundException {
        if (solicitudRenting == null) {
            throw new SolicitudRentingNotFoundException(HttpStatus.NOT_FOUND);
        }
    }

}
