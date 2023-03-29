package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private final SolicitudRentingMapper solicitudRentingMapper;
    private final PersonaMapper personaMapper;

    public SolicitudRentingServiceImpl (SolicitudRentingMapper solicitudRentingMapper, PersonaMapper personaMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
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
