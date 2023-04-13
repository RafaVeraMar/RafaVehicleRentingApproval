package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

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

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper, TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper, PersonaService personaService, CodigoResolucionValidator codigoResolucionValidator) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
        this.personaService = personaService;
        this.codigoResolucionValidator = codigoResolucionValidator;

    }

    @Override
    public SolicitudRenting addSolicitudRenting(SolicitudRenting solicitudRenting) throws RequestApiValidationException {
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        validateNumVehiculos(solicitudRenting);
        validateInversion(solicitudRenting);
        validateCuota(solicitudRenting);
        validatePlazo(solicitudRenting);
        validateFecha(solicitudRenting);
        solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
        solicitudRenting.setPersona(personaService.getPerson(solicitudRenting.getPersona().getPersonaId()));
        return solicitudRenting;
    }


    private void existIdPersona(int idPersona) throws PersonaNotFoundException {
        if (!personaService.existePersona(idPersona)) {
            throw new PersonaNotFoundException(idPersona);
        }
    }

    @Override
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException, EstadoSolicitudInvalidException {
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(idSolicitud);

        if (codigoExiste == 0) { //idSolicitud or codResolucion null
            throw new EstadoSolicitudNotFoundException();
        }

        TipoResultadoSolicitud resultadoSolicitud = this.tipoResultadoSolicitudMapper.getResultadoSolicitud(idSolicitud);
        this.validarCodigoResolucion(resultadoSolicitud.getCodResultado());

        return resultadoSolicitud.getDescripcion();


    }

    private void validarCodigoResolucion(String CodResolucion) throws EstadoSolicitudInvalidException {
        this.codigoResolucionValidator.validarCodResolucion(CodResolucion);

    }

    /**
     * Implementación del método que busca una solicitud por su ID
     *
     * @param id Es el ID de la solicitud que se quiere buscar
     * @return Devuelve un objeto Solicitud Renting
     * @throws RequestApiValidationException
     */

    public SolicitudRenting getSolicitudById(int id) throws RequestApiValidationException {
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        validateSolicitudRenting(solicitudRenting);
        return solicitudRenting;
    }

    @Override
    public void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws SolicitudRentingNotFoundException, EstadoSolicitudNotFoundException {

        List<String> posiblesEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        int existeEstado = this.solicitudRentingMapper.existeSolicitud(solicitudId);

        if (!posiblesEstados.contains(nuevoEstado.getCodResultado())) {
            throw new EstadoSolicitudNotFoundException();
        }
        if (existeEstado == 0) {
            throw new SolicitudRentingNotFoundException();
        }

        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId, nuevoEstado);
        System.out.println("\n\nCambios en tu solicitud.\nSu solicitud se encuentra: " + this.tipoResultadoSolicitudMapper.getEstadoSolicitud(solicitudId));

    }

    @Override
    public List<String> getListaEstados() {
        List<String> listaEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        return listaEstados;
    }

    private int lenghtNumber(BigInteger number) {
        if (number != null) {
            String numeroString = number.toString();
            return numeroString.length();
        }
        return 0;
    }

    private void validatePersona(int idPersona) throws PersonaNotFoundException {
        existIdPersona(idPersona);
    }

    private void validateNumVehiculos(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, InputIsNullOrIsEmpty, InputIsNegativeOrZeroException {
        if (lenghtNumber(solicitudRenting.getNumVehiculos()) > 38) {
            throw new WrongLenghtFieldException("numVehiculos");
        }
        if (solicitudRenting.getNumVehiculos() == null) {
            throw new InputIsNullOrIsEmpty("numVehivulos");
        }
        if (solicitudRenting.getNumVehiculos().signum() == -1 || solicitudRenting.getNumVehiculos().signum() == 0) {
            throw new InputIsNegativeOrZeroException("numVehiculos");
        }
    }

    private void validateInversion(SolicitudRenting solicitudRenting) throws InputIsNullOrIsEmpty, InputIsNegativeOrZeroException {
        if (solicitudRenting.getInversion() == null) {
            throw new InputIsNullOrIsEmpty("inversion");
        }
        if (solicitudRenting.getInversion() < 1) {
            throw new InputIsNegativeOrZeroException("inversion");
        }
    }

    private void validateCuota(SolicitudRenting solicitudRenting) throws InputIsNullOrIsEmpty, InputIsNegativeOrZeroException {
        if (solicitudRenting.getCuota() == null) {
            throw new InputIsNullOrIsEmpty("cuota");
        }
        if (solicitudRenting.getCuota() < 1) {
            throw new InputIsNegativeOrZeroException("cuota");
        }
    }

    private void validatePlazo(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, InputIsNegativeOrZeroException, InputIsNullOrIsEmpty {
        if (solicitudRenting.getPlazo() != null) {
            if (lenghtNumber(solicitudRenting.getPlazo()) > 38) {
                throw new WrongLenghtFieldException("Plazo");
            }
            if (solicitudRenting.getPlazo().signum() == -1 || solicitudRenting.getPlazo().signum() == 0) {
                throw new InputIsNegativeOrZeroException("plazo");
            }
        }
    }

    private void validateFecha(SolicitudRenting solicitudRenting) throws DateIsBeforeException {
        if (solicitudRenting.getFechaInicioVigor() != null && solicitudRenting.getFechaResolucion() != null) {
            if (solicitudRenting.getFechaInicioVigor().before(solicitudRenting.getFechaResolucion())) {
                throw new DateIsBeforeException("fechaInicioVigo", "fechaResolucion");
            }
        }
    }

    public void cancelarSolicitud(int id) throws RequestApiValidationException {
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
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
            throw new NullPointerException();
        }
    }

}
