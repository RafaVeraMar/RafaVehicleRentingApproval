package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.*;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.ProfesionService;
import com.babel.vehiclerentingapproval.services.RentaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Esta clase es la implementación del método crear del CRUD y del metodo para validar y comprobar las renta.
 *
 * @author adres.guijarro@babelgroup.com
 * @see RentaService
 */
@Service
@Log4j2
public class RentaServiceImpl implements RentaService {
    RentaMapper rentaMapper;


    PersonaService personaService;

    ProfesionService profesionService;

    public RentaServiceImpl (RentaMapper rentaMapper, PersonaService personaService, ProfesionService profesionService) {
        log.info("En el constructor de RentaServiceImpl");
        this.rentaMapper = rentaMapper;
        this.personaService = personaService;
        this.profesionService = profesionService;
        log.info("Saliendo del constructor de RentaServiceImpl");
    }


    /**
     * Metodo que agrega una nueva renta, realizando varias validaciones antes de insertar la renta en la base de datos.
     *
     * @param renta la renta que se va a añadir
     * @throws EstadoSolicitudInvalidException si el codigo de resolucion no es valido
     * @see ProfesionServiceImpl
     * @see PersonaServiceImpl
     * @see #validateRenta(int)
     */
    @Override
    public Renta addRenta (Renta renta) {
        log.info("Entrando en addRenta");
        this.profesionService.validateProfesion(renta.getProfesion().getProfesionId());
        this.personaService.validatePersona(renta.getPersona().getPersonaId());
        this.validateRenta(renta.getRentaId());
        this.rentaMapper.addRenta(renta);
        log.info("Saliendo de addRenta");
        return renta;
    }

    /**
     * Comprueba si una renta existe.
     *
     * @param rentaId es el id de la renta
     * @throws RentaFoundException lanza una excepcion cuando la renta ya existe en la base de datos.
     */
    public void validateRenta (int rentaId) {
        log.info("Entrando en validateRenta");
        if (this.existeRenta(rentaId)) {
            log.error("La renta no existe");
            throw new RentaFoundException(HttpStatus.NOT_FOUND);
        }
        log.info("Saliendo de validateRenta");
    }

    /**
     * Metodo que implementa si una renta existe o no.
     *
     * @param rentaId es el id de la renta
     */
    public boolean existeRenta (int rentaId) {
        log.info("Entrando en existeRenta");
        return this.rentaMapper.existeRenta(rentaId) != 0;
    }

}
