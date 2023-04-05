package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigInteger;

import static com.babel.vehiclerentingapproval.services.impl.EmailServiceImpl.SendMail;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private SolicitudRentingMapper solicitudRentingMapper;
    private TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    private final PersonaMapper personaMapper;
    private final CodigoResolucionValidator codigoResolucionValidator;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper,TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper, PersonaMapper personaMapper,CodigoResolucionValidator codigoResolucionValidator) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
        this.personaMapper = personaMapper;
        this.codigoResolucionValidator = codigoResolucionValidator;
    }

    @Override
    public SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) throws PersonaNotFoundException, WrongLenghtFieldException, InputIsNullOrIsEmpty, DateIsBeforeException, InputIsNegativeOrZeroException {
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        validateNumVehiculos(solicitudRenting);
        validateInversion(solicitudRenting);
        validateCuota(solicitudRenting);
        validatePlazo(solicitudRenting);
        validateFecha(solicitudRenting);
        solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
        return solicitudRenting;
    }


    private void existIdPersona (int idPersona) throws PersonaNotFoundException {
        if (personaMapper.existePersona(idPersona) < 1) {
            throw new PersonaNotFoundException(idPersona);
        }
    }

    @Override
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException, EstadoSolicitudInvalidException {
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(idSolicitud);

        if(codigoExiste == 0){ //idSolicitud or codResolucion null
            throw new EstadoSolicitudNotFoundException();
        }

        TipoResultadoSolicitud resultadoSolicitud = this.tipoResultadoSolicitudMapper.getResultadoSolicitud(idSolicitud);
        this.validarCodigoResolucion(resultadoSolicitud.getCodResultado());

        return resultadoSolicitud.getDescripcion();


    }
    private void validarCodigoResolucion(String CodResolucion) throws EstadoSolicitudInvalidException{
        this.codigoResolucionValidator.validarCodResolucion(CodResolucion);

    }

    public SolicitudRenting getSolicitudById (int id) throws SolicitudRentingNotFoundException {
        int existe = this.solicitudRentingMapper.existeSolicitud(id);
        SolicitudRenting solicitudRenting;
        if (existe == 1) {
            solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        } else {
            throw new SolicitudRentingNotFoundException();
        }

        return solicitudRenting;
    }

    @Override
    public void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws SolicitudRentingNotFoundException, EstadoSolicitudNotFoundException{

        List<String> posiblesEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        int existeEstado = this.solicitudRentingMapper.existeSolicitud(solicitudId);

        if(!posiblesEstados.contains(nuevoEstado.getCodResultado())){
            throw new EstadoSolicitudNotFoundException();
        }
        if (existeEstado == 0) {
            throw new SolicitudRentingNotFoundException();
        }

        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId,nuevoEstado);

        //Notificaciones por email

        SolicitudRenting solicitudRenting=this.solicitudRentingMapper.getSolicitudByID(solicitudId);

        String mensaje= "Su solicitud con ID: "+ solicitudId + ", se encuentra: " + this.tipoResultadoSolicitudMapper.getEstadoSolicitud(solicitudId);
        String asunto="Cambios en tu solicitud";
        String destinatario = personaMapper.getEmail(solicitudRenting.getPersona().getPersonaId());
        SendMail(mensaje,destinatario,asunto);

    }
    @Override
    public List<String> getListaEstados() {
        List<String> listaEstados =  this.tipoResultadoSolicitudMapper.getListaEstados();
        return listaEstados;
    }

    private int lenghtNumber (BigInteger number) {
        if (number != null) {
            String numeroString = number.toString();
            return numeroString.length();
        }
        return 0;
    }

    private void validatePersona (int idPersona) throws PersonaNotFoundException {
        existIdPersona(idPersona);
    }

    private void validateNumVehiculos (SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, InputIsNullOrIsEmpty, InputIsNegativeOrZeroException {
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

    private void validateInversion (SolicitudRenting solicitudRenting) throws InputIsNullOrIsEmpty, InputIsNegativeOrZeroException {
        if (solicitudRenting.getInversion() == null) {
            throw new InputIsNullOrIsEmpty("inversion");
        }
        if (solicitudRenting.getInversion() < 1) {
            throw new InputIsNegativeOrZeroException("inversion");
        }
    }

    private void validateCuota (SolicitudRenting solicitudRenting) throws InputIsNullOrIsEmpty, InputIsNegativeOrZeroException {
        if (solicitudRenting.getCuota() == null) {
            throw new InputIsNullOrIsEmpty("cuota");
        }
        if (solicitudRenting.getCuota() < 1) {
            throw new InputIsNegativeOrZeroException("cuota");
        }
    }

    private void validatePlazo (SolicitudRenting solicitudRenting) throws WrongLenghtFieldException, InputIsNegativeOrZeroException, InputIsNullOrIsEmpty {
        if (solicitudRenting.getPlazo() != null) {
            if (lenghtNumber(solicitudRenting.getPlazo()) > 38) {
                throw new WrongLenghtFieldException("Plazo");
            }
            if (solicitudRenting.getPlazo().signum() == -1 || solicitudRenting.getPlazo().signum() == 0) {
                throw new InputIsNegativeOrZeroException("plazo");
            }
        }
    }

    private void validateFecha (SolicitudRenting solicitudRenting) throws DateIsBeforeException {
        if (solicitudRenting.getFechaInicioVigor() != null && solicitudRenting.getFechaResolucion() != null) {
            if (solicitudRenting.getFechaInicioVigor().before(solicitudRenting.getFechaResolucion())) {
                throw new DateIsBeforeException("fechaInicioVigo", "fechaResolucion");
            }
        }
    }

    public void cancelarSolicitud (int id) throws SolicitudRentingNotFoundException {
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        if (solicitudRenting == null) {
            throw new SolicitudRentingNotFoundException();
        }
        solicitudRentingMapper.cancelarSolicitud(solicitudRenting);
    }

}
