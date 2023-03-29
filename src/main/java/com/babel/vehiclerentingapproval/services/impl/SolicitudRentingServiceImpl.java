package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private SolicitudRentingMapper solicitudRentingMapper;
    private TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    private final PersonaMapper personaMapper;

    public SolicitudRentingServiceImpl(SolicitudRentingMapper solicitudRentingMapper,TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper, PersonaMapper personaMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.tipoResultadoSolicitudMapper = tipoResultadoSolicitudMapper;
        this.personaMapper = personaMapper;
    }

    @Override
    public SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) throws PersonaNotFoundException, WrongLenghtFieldException, InputIsNull {
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        validateNumber(solicitudRenting);
        validateNotNull(solicitudRenting);
        //TODO: falta validaciones de fechas
         solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
         //TODO: introducir el estado de la resolucion
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
        return "Estado de la solicitud con id " + idSolicitud + ": " + estado;
    }
    public SolicitudRenting getSolicitudById(int id) throws SolicitudRentingNotFoundException {
        SolicitudRenting solicitudRenting = this.solicitudRentingMapper.getSolicitudByID(id);

        if (solicitudRenting == null){
            throw new SolicitudRentingNotFoundException();
        }
        return solicitudRenting;
    }

    @Override
    public void modificaSolicitud(Integer solicitudId, SolicitudRenting nuevoRenting) {
        this.solicitudRentingMapper.modificaSolicitud(solicitudId,nuevoRenting);
    }

     private int lenghtNumber(BigInteger number){
        String numeroString = number.toString();
         return numeroString.length();
     }

    private void validatePersona(int idPersona) throws PersonaNotFoundException {
        existIdPersona(idPersona);
    }

     private void validateNumber(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException {
        if(lenghtNumber(solicitudRenting.getNumVehiculos()) > 38 || lenghtNumber(solicitudRenting.getPlazo()) > 38){
            throw new WrongLenghtFieldException();
        }
     }

     private void validateNotNull(SolicitudRenting solicitudRenting) throws InputIsNull {
        if(solicitudRenting.getInversion() == null || solicitudRenting.getCuota() == null || solicitudRenting.getNumVehiculos() == null){
            throw new InputIsNull();
        }
     }
}
