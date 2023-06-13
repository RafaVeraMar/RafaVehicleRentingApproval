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
import io.micrometer.core.annotation.Timed;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;



/**
 * Esta clase es la implementación de los métodos CRUD (Crear, Ver, Modificar y Cancelar) de las Solicitudes de Renting.
 *
 * @author miguel.sdela@babelgroup.com, javier.serrano@babelgroup.com, ramon.vazquez@babelgroup.com, alvaro.aleman@babelgroup.com, javier.roldan@babelgroup.com
 * @see SolicitudRentingService
 */
@Service
@Log4j2
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private final SolicitudRentingMapper solicitudRentingMapper;
    private final TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    private final PersonaService personaService;
    private final CodigoResolucionValidator codigoResolucionValidator;

    private final PersonaMapper personaMapper;
    private final EmailService emailService;

    private static final String FILE_NAME = "registroSolicitudRenting.txt";

    private static int lastId = 0;

    public SolicitudRentingServiceImpl (SolicitudRentingMapper solicitudRentingMapper, TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper, PersonaService personaService, CodigoResolucionValidator codigoResolucionValidator, PersonaMapper personaMapper, EmailService emailService) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
        this.personaService = personaService;
        this.codigoResolucionValidator = codigoResolucionValidator;
        this.personaMapper = personaMapper;
        this.emailService = emailService;

    }

    public void registrarSolicitudEnArchivo(SolicitudRenting solicitudRenting) {


        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String lastLine = null;
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
                if (lastLine != null) {
                    String[] parts = lastLine.split(",");
                    if (parts.length == 2) {
                        lastId = Integer.parseInt(parts[0]);
                    }
                }
            } catch (IOException e) {
                log.error("Error al leer el archivo de registro de solicitud", e);
            }
        }

        lastId++;

        LocalDateTime fechaHoraActual = LocalDateTime.now();
        String registro = String.format("%d,%s%n", lastId, fechaHoraActual.toString() + ", id_Solicitud: " + String.valueOf(solicitudRenting.getSolicitudId()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(registro);
        } catch (IOException e) {
            log.error("Error al guardar registro de solicitud en archivo", e);
        }
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
    public int addSolicitudRenting (SolicitudRenting solicitudRenting)  {
        log.info("Iniciando el proceso para agregar una nueva solicitud de renting");

        log.debug("Validando la persona con ID: {}", solicitudRenting.getPersona().getPersonaId());
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        log.debug("Persona validada correctamente");

        log.debug("Validando el número de vehículos de la solicitud");
        validateNumVehiculos(solicitudRenting);
        log.debug("Número de vehículos validado correctamente");

        log.debug("Validando la inversión de la solicitud");
        validateInversion(solicitudRenting);
        log.debug("Inversión validada correctamente");

        log.debug("Validando la cuota de la solicitud");
        validateCuota(solicitudRenting);
        log.debug("Cuota validada correctamente");

        log.debug("Validando el plazo de la solicitud");
        validatePlazo(solicitudRenting);
        log.debug("Plazo validado correctamente");

        log.debug("Validando la fecha de la solicitud");
        validateFecha(solicitudRenting);
        log.debug("Fecha validada correctamente");

        log.info("Insertando la solicitud de renting en la base de datos");
        solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
        log.info("Solicitud de renting insertada correctamente con ID: {}", solicitudRenting.getSolicitudId());

        log.debug("Asociando la persona con ID: {} a la solicitud de renting", solicitudRenting.getPersona().getPersonaId());
        solicitudRenting.setPersona(personaService.invalidPersonId(solicitudRenting.getPersona().getPersonaId()));
        log.debug("Persona asociada correctamente a la solicitud de renting");

        // Llama al nuevo método para registrar la solicitud en el archivo
        registrarSolicitudEnArchivo(solicitudRenting);

        log.info("Finalizando el proceso para agregar una nueva solicitud de renting con éxito");

        return solicitudRenting.getSolicitudId();
    }

    /**
     * Comprueba si existe una persona con el identificador proporcionado.
     *
     * @param idPersona el identificador de la persona a verificar
     * @throws PersonaNotFoundException si no se encuentra una persona con el identificador proporcionado
     */
    private void existIdPersona (int idPersona) {
        log.debug("Verificando la existencia de la persona con ID: {}", idPersona);

        if (!personaService.existePersona(idPersona)) {
            log.warn("Persona no encontrada con ID: {}", idPersona);
            throw new PersonaNotFoundException(idPersona, HttpStatus.NOT_FOUND);
        }

        log.debug("Persona con ID: {} encontrada correctamente", idPersona);
    }

    /**
     * Devuelve el estado de una solicitud de renting a partir de su id
     *
     * @param idSolicitud el ID de la solicitud a consultar
     * @return el estado de la solicitud como una cadena de caracteres
     * @throws RequestApiValidationException si la id de la solicitud no existe, el codigo de resolucion es nulo, o no es valido
     */
    @Override
    public String verEstadoSolicitud (String idSolicitud) {
        log.info("Verificando el estado de la solicitud con ID: {}", idSolicitud);
        int id;

        try {
            id = Integer.parseInt(idSolicitud);
        } catch (NumberFormatException e) {
            log.error("Formato de ID incorrecto: {}", idSolicitud, e);
            throw new IdIncorrectFormatException(HttpStatus.BAD_REQUEST);
        }

        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(id);

        validarCodResolucionExiste(codigoExiste);

        TipoResultadoSolicitud resultadoSolicitud = this.tipoResultadoSolicitudMapper.getResultadoSolicitud(id);
        this.validarCodigoResolucion(resultadoSolicitud.getCodResultado());

        log.info("Estado de la solicitud con ID {}: {}", idSolicitud, resultadoSolicitud.getDescripcion());

        return resultadoSolicitud.getDescripcion();


    }

    /**
     * Comprueba que el codigo de resolucion de la solicitud existe.
     *
     * @param codResolucion Valor encontrado al hacer la consulta en la base de datos
     * @throws EstadoSolicitudNotFoundException si el codigo de resolución es nulo o el id de la solicitud no existe
     */
    private void validarCodResolucionExiste (int codResolucion) {
        log.debug("Validando si el código de resolución existe: {}", codResolucion);

        if (codResolucion == 0) { //idSolicitud or codResolucion null
            log.warn("El código de resolución no existe");
            throw new EstadoSolicitudNotFoundException(HttpStatus.NOT_FOUND);
        }

        log.debug("El código de resolución existe");
    }

    /**
     * Método que comprueba si el codigo de la resolución es válido
     *
     * @param codResolucion el codigo de la resolucion
     * @throws EstadoSolicitudInvalidException si el codigo de resolucion no es valido
     * @see CodigoResolucionValidatorImpl
     */
    private void validarCodigoResolucion (String codResolucion) {
        log.debug("Validando el código de resolución: {}", codResolucion);

        this.codigoResolucionValidator.validarCodResolucion(codResolucion);

        log.debug("Código de resolución validado correctamente");

    }

    /**
     * Implementación del método que busca una solicitud por su ID
     *
     * @param id Es el ID de la solicitud que se quiere buscar
     * @return Devuelve un objeto Solicitud Renting
     * @throws RequestApiValidationException
     */


    public SolicitudRenting getSolicitudById (int id) {
        log.info("Buscando la solicitud de renting con ID: {}", id);

        var solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);

        log.debug("Validando la solicitud de renting");
        validateSolicitudRenting(solicitudRenting);
        log.debug("Solicitud de renting validada correctamente");

        log.info("Solicitud de renting encontrada con éxito");

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
    public void modificaEstadoSolicitud (Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws MessagingException {

        log.info("Modificando el estado de la solicitud con ID: {}", solicitudId);

        List<String> posiblesEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        int existeEstado = this.solicitudRentingMapper.existeSolicitud(solicitudId);
        SolicitudRenting solicitud = this.solicitudRentingMapper.getSolicitudByID(solicitudId);

        if (!posiblesEstados.contains(nuevoEstado.getCodResultado())) {
            log.warn("Estado de solicitud no encontrado");
            throw new EstadoSolicitudNotFoundException(HttpStatus.NOT_FOUND);
        }
        if (existeEstado == 0) {
            log.warn("Solicitud de renting no encontrada con ID: {}", solicitudId);
            throw new SolicitudRentingNotFoundException(HttpStatus.NOT_FOUND);
        }

        String email = this.personaMapper.getEmail(solicitud.getPersona().getPersonaId());
        if (email == null || email.indexOf('@') == -1) {
            log.error("Error al enviar el correo electrónico a: {}", email);
            throw new FailedSendingEmail(HttpStatus.BAD_REQUEST, email);
        }

        var estadoSolicitud = this.tipoResultadoSolicitudMapper.getEstadoSolicitud(solicitudId);
        log.debug("Enviando notificación por correo electrónico a: {}", email);
        emailService.sendMail("Su solicitud se encuentra: " + estadoSolicitud, email, "Cambios en tu solicitud");
        log.debug("Correo electrónico enviado correctamente");

        log.info("Actualizando el estado de la solicitud con ID: {} al estado: {}", solicitudId, nuevoEstado.getCodResultado());
        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId, nuevoEstado);
        log.info("Estado de la solicitud actualizado correctamente");
    }


    /**
     * Modifica únicamete el estado de una solicitud de renting, se comprueba a través de la base de datos que el nuevo estado sea uno de los valores posible.
     *
     * @return List<String> (llamada a base de datos (mapper) para recoger posibles estados.
     */
    @Override
    public List<String> getListaEstados ( ) {
        log.info("Obteniendo la lista de estados de las solicitudes");

        List<String> listaEstados = this.tipoResultadoSolicitudMapper.getListaEstados();

        if (listaEstados.isEmpty()) {
            log.warn("La lista de estados de las solicitudes está vacía");
        } else {
            log.info("Se obtuvo la lista de estados de las solicitudes con éxito");
        }

        return listaEstados;
    }

    /**
     * Calcula la cantidad de dígitos en un objeto BigInteger.
     *
     * @param number el objeto BigInteger cuya cantidad de dígitos se desea calcular
     * @return la cantidad de dígitos en el objeto BigInteger; si el objeto BigInteger es nulo, devuelve 0
     */
    private int lenghtNumber(BigInteger number) {
        log.info("Procesando el tamaño del numero");
        if (number != null) {
            var numeroString = number.toString();
            return numeroString.length();
        }
        log.info("Tamaño del numero procesado correctamente");
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
        log.info("procesando el validar persona");
        existIdPersona(idPersona);
        log.info("La persona fue validada");
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
        log.info("Validando el numero de vehiculos de la solicitud");
        if (lenghtNumber(solicitudRenting.getNumVehiculos()) > 38) {
            log.info("El numero de vehiculos es mayor a 38");
            throw new WrongLenghtFieldException("numVehiculos", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getNumVehiculos() == null) {
            log.error("El numero de vehiculos es nulo");
            throw new InputIsNullOrIsEmpty("numVehivulos", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getNumVehiculos().signum() == -1 || solicitudRenting.getNumVehiculos().signum() == 0) {
            log.error("El numero de vehiculos es incorrecto");
            throw new InputIsNegativeOrZeroException("numVehiculos", HttpStatus.BAD_REQUEST);
        }
        log.info("El numero de vehiculos ha sido validado");
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
        log.info("Validando la inversion de la solicitud de renting");
        if (solicitudRenting.getInversion() == null) {
            log.error("la inversion es nula");
            throw new InputIsNullOrIsEmpty("inversion", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getInversion() < 1) {
            log.error("la inversion es menor que 1");
            throw new InputIsNegativeOrZeroException("inversion", HttpStatus.BAD_REQUEST);
        }
        log.info("La inversion ha sido validada");
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
        log.info("Validando la couta");
        if (solicitudRenting.getCuota() == null) {
            log.error("la cuota es nula");
            throw new InputIsNullOrIsEmpty("cuota", HttpStatus.BAD_REQUEST);
        }
        if (solicitudRenting.getCuota() < 1) {
            log.error("la cuota es menor a 1");
            throw new InputIsNegativeOrZeroException("cuota", HttpStatus.BAD_REQUEST);
        }
        log.info("La cuota ha sido validada");
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
        log.info("Validando el plazo");
        if (solicitudRenting.getPlazo() != null) {
            if (lenghtNumber(solicitudRenting.getPlazo()) > 38) {
                log.error("El plazo es mayor a 38");
                throw new WrongLenghtFieldException("Plazo", HttpStatus.BAD_REQUEST);
            }
            if (solicitudRenting.getPlazo().signum() == -1 || solicitudRenting.getPlazo().signum() == 0) {
                log.error("El plazo es negativo o 0");
                throw new InputIsNegativeOrZeroException("plazo", HttpStatus.BAD_REQUEST);
            }
        }
        log.info("Validado el plazo");
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
        log.info("Validando la fecha");
        if ((solicitudRenting.getFechaInicioVigor() != null && solicitudRenting.getFechaResolucion() != null)
                && (solicitudRenting.getFechaInicioVigor().before(solicitudRenting.getFechaResolucion()))) {
            log.error("Error en las fechas de la solicitud");
            throw new DateIsBeforeException("fechaInicioVigo", "fechaResolucion",HttpStatus.BAD_REQUEST);
        }
        log.info("Las fechas han sido validadas");
    }

    /**
     * Servicio que se encarga de cancelar la solicitud de renting asociada al id que se le pasa como parametro, solicitud que encontramos gracias al uso del metodo getSolicitudById
     *
     * @param id de la solicitud de renting
     * @throws SolicitudRentingNotFoundException que recoge la excepcion cuando la solicitud de renting es nula, si la solicitud no es nula la devuelve cancelada
     */
    public void cancelarSolicitud(int id) {
        log.info("Cancelando la solicitud de renting");
        var solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        validateSolicitudRenting(solicitudRenting);
        solicitudRentingMapper.cancelarSolicitud(solicitudRenting);
        log.info("La solicitud ha sido cancelada");
    }

    /**
     * Validacion de si una Solicitud de Renting es nula o no
     *
     * @param solicitudRenting solicitud a comprobar si es nula
     * @throws SolicitudRentingNotFoundException
     */
    public void validateSolicitudRenting(SolicitudRenting solicitudRenting) {
        log.info("Validando la solicitud de renting");
        if (solicitudRenting == null) {
            log.warn("La solicitud no ha sido encontrada");
            throw new SolicitudRentingNotFoundException(HttpStatus.NOT_FOUND);
        }
        log.info("La solicitud ha sido validada");
    }

}
