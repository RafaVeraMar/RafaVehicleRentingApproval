package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.exceptions.RequiredMissingFieldException;
import com.babel.vehiclerentingapproval.exceptions.WrongLenghtFieldException;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.springframework.stereotype.Service;

@Service
public class SolicitudRentingServiceImpl implements SolicitudRentingService {
    private final SolicitudRentingMapper solicitudRentingMapper;
    private final PersonaMapper personaMapper;

    public SolicitudRentingServiceImpl (SolicitudRentingMapper solicitudRentingMapper, PersonaMapper personaMapper) {
        this.solicitudRentingMapper = solicitudRentingMapper;
        this.personaMapper = personaMapper;
    }

    @Override
    public SolicitudRenting addSolicitudRenting (SolicitudRenting solicitudRenting) throws RequestApiValidationException, PersonaNotFoundException {
        validatePersona(solicitudRenting.getPersona().getPersonaId());
        validateNumber(solicitudRenting);
        validateNotNull(solicitudRenting);
        //TODO: falta validaciones de fechas
         solicitudRentingMapper.insertSolicitudRenting(solicitudRenting);
         //TODO: introducir el estado de la resolucion
         return solicitudRenting;
    }

    private void validatePersona(int idPersona) throws RequestApiValidationException, PersonaNotFoundException {
        existIdPersona(idPersona);
    }

    private void existIdPersona (int idPersona) throws PersonaNotFoundException {
        if (personaMapper.existePersona(idPersona) < 1){
            throw new PersonaNotFoundException();
        }
    }

     private int lenghtNumber(Long number){
        String numeroString = Long.toString(number);
        return numeroString.length();
     }

     private void validateNumber(SolicitudRenting solicitudRenting) throws WrongLenghtFieldException {
        if(lenghtNumber(solicitudRenting.getNumVehiculos()) > 38 || solicitudRenting.getPlazo() > 38){
            throw new WrongLenghtFieldException();
        }
     }

     private void validateNotNull(SolicitudRenting solicitudRenting) throws RequiredMissingFieldException {
        if(solicitudRenting.getInversion() == null || solicitudRenting.getCuota() == null || solicitudRenting.getNumVehiculos() == null){
            throw new RequiredMissingFieldException();
        }
     }
}
