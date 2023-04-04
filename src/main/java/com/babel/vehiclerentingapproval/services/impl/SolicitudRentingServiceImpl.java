package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigInteger;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private SolicitudRentingMapper solicitudRentingMapper;
    private TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    private EmailServiceImpl emailService;
    private final PersonaMapper personaMapper;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper,TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper, PersonaMapper personaMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
        this.personaMapper = personaMapper;
    }

    @Override
    public SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) throws PersonaNotFoundException, WrongLenghtFieldException, InputIsNullOrIsEmpty, DateIsBeforeException {
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        validateNumber(solicitudRenting);
        validateNotNullOrIsEmpty(solicitudRenting);
        validateFecha(solicitudRenting);
         solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
         return solicitudRenting;
    }


    private void existIdPersona (int idPersona) throws PersonaNotFoundException {
        if (personaMapper.existePersona(idPersona) < 1){
            throw new PersonaNotFoundException();
        }
    }

    @Override
    public String verEstadoSolicitud(int idSolicitud) throws EstadoSolicitudNotFoundException {
        int codigoExiste = tipoResultadoSolicitudMapper.existeCodigoResolucion(idSolicitud);

        if(codigoExiste == 0){
            throw new EstadoSolicitudNotFoundException();
        }
        String estado = tipoResultadoSolicitudMapper.getEstadoSolicitud(idSolicitud);
        return estado;
    }

    public SolicitudRenting getSolicitudById(int id) throws SolicitudRentingNotFoundException {
        int existe = this.solicitudRentingMapper.existeSolicitud(id);
        SolicitudRenting solicitudRenting;
        if (existe == 1) {
            solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        }else{
            throw new SolicitudRentingNotFoundException();
        }

        return solicitudRenting;
    }

    @Override
    public void modificaEstadoSolicitud(Integer solicitudId, TipoResultadoSolicitud nuevoEstado) throws SolicitudRentingNotFoundException, EstadoSolicitudNotFoundException{

        List<String> posiblesEstados = this.tipoResultadoSolicitudMapper.getListaEstados();
        int existeEstado = this.solicitudRentingMapper.existeSolicitud(solicitudId);

        SolicitudRenting solicitud = getSolicitudById(solicitudId);

        this.emailService.sendSimpleMessage(solicitud.getPersona().getEmail(),"Cambios en tu solicitud", "Su solicitud se encuentra: " + nuevoEstado.getDescripcion());
        if(!posiblesEstados.contains(nuevoEstado.getCodResultado())){
            throw new EstadoSolicitudNotFoundException();
        }
        if (existeEstado == 0) {
            throw new SolicitudRentingNotFoundException();
        }

        this.solicitudRentingMapper.modificaEstadoSolicitud(solicitudId,nuevoEstado);
        this.emailService.sendSimpleMessage(solicitud.getPersona().getEmail(),"Cambios en tu solicitud", "Su solicitud se encuentra: " + nuevoEstado.getDescripcion());

    }
    @Override
    public List<String> getListaEstados() {
        List<String> listaEstados =  this.tipoResultadoSolicitudMapper.getListaEstados();
        return listaEstados;
    }

     private int lenghtNumber(BigInteger number){
        if(number != null){
            String numeroString = number.toString();
            return numeroString.length();
        }
        return 0;
     }

    private void validatePersona(int idPersona) throws PersonaNotFoundException {
        existIdPersona(idPersona);
    }

     private void validateNumber(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException {
        if(lenghtNumber(solicitudRenting.getNumVehiculos()) > 38 || lenghtNumber(solicitudRenting.getPlazo()) > 38){
            throw new WrongLenghtFieldException();
        }
     }

     private void validateNotNullOrIsEmpty (SolicitudRenting solicitudRenting) throws InputIsNullOrIsEmpty {
        if(solicitudRenting.getNumVehiculos() == null || solicitudRenting.getNumVehiculos().toString().isEmpty()
                || solicitudRenting.getInversion() == null || solicitudRenting.getInversion().toString().isEmpty()
                    || solicitudRenting.getCuota() == null || solicitudRenting.getCuota().toString().isEmpty()){
            throw new InputIsNullOrIsEmpty();
        }
     }

     private void validateFecha(SolicitudRenting solicitudRenting) throws DateIsBeforeException {
            if(solicitudRenting.getFechaInicioVigor() != null && solicitudRenting.getFechaResolucion() != null){
                if(solicitudRenting.getFechaInicioVigor().before(solicitudRenting.getFechaResolucion())){
                    throw new DateIsBeforeException();
                }
            }
     }
    public void cancelarSolicitud(int id) throws SolicitudRentingNotFoundException{
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);
        if(solicitudRenting==null){
            throw new SolicitudRentingNotFoundException();
        }
        solicitudRentingMapper.cancelarSolicitud(solicitudRenting);
    }

}
