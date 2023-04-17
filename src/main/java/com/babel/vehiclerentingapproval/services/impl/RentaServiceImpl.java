package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import com.babel.vehiclerentingapproval.services.RentaService;
import org.springframework.stereotype.Service;
/**
 * Esta clase es la implementación del método crear del CRUD y del metodo para validar y comprobar las renta.
 *
 * @author adres.guijarro@babelgroup.com
 * @see RentaService
 */
@Service
public class RentaServiceImpl implements RentaService {
    RentaMapper rentaMapper;


    PersonaService personaService;

    ProfesionService profesionService;

    public RentaServiceImpl (RentaMapper rentaMapper, PersonaService personaService, ProfesionService profesionService) {
        this.rentaMapper = rentaMapper;
        this.personaService = personaService;
        this.profesionService = profesionService;
    }


    /**
     * Metodo que agrega una nueva renta, realizando varias validaciones antes de insertar la renta en la base de datos.
     * @param renta la renta que se va a añadir
     * @throws EstadoSolicitudInvalidException si el codigo de resolucion no es valido
     * @see ProfesionServiceImpl
     * @see PersonaServiceImpl
     * @see #validateRenta(int)
     */
    @Override
    public Renta addRenta (Renta renta) throws ProfesionNotFoundException, PersonaNotFoundException, RentaFoundException {
        this.profesionService.validateProfesion(renta.getProfesion().getProfesionId());
        this.personaService.validatePersona(renta.getPersona().getPersonaId());
        this.validateRenta(renta.getRentaId());
        this.rentaMapper.addRenta(renta);
        return renta;
    }

    /**
     * Comprueba si una renta existe.
     * @param rentaId es el id de la renta
     * @throws RentaFoundException lanza una excepcion cuando la renta ya existe en la base de datos.
     */
    public void validateRenta (int rentaId) throws RentaFoundException {
        if (this.existeRenta(rentaId)) {
            throw new RentaFoundException();
        }
    }
    /**
     * Metodo que implementa si una renta existe o no.
     * @param rentaId es el id de la renta
     */
    public boolean existeRenta (int rentaId) {
        if (this.rentaMapper.existeRenta(rentaId) != 0) {
            return true;
        }
        return false;
    }

}
