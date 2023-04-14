package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.exceptions.PersonaNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.ProfesionNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.RentaFoundException;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.models.Renta;
/**
 * Esta clase SERVICE sirve como interfaz de las operaciones para hacer el CRUD de crear, validar una renta y comprobar que existe una renta
 *
 * @author andres.guijarro@babelgroup.com
 */
public interface RentaService {
    /**
     * Agrega una renta al sistema.
     * Valida la información proporcionada en el objeto Renta antes de agregarlo.
     * Si la solicitud no cumple con los criterios de validación, se lanza una excepción de tipo ProfesionNotFoundException o PersonaNotFoundException o RentaFoundException.
     * @param renta objeto renta que contiene la información de la renta a agregar
     * @return Renta agregada y actualizada con el ID generado en el sistema
     * @throws ProfesionNotFoundException si la renta no cumple con los criterios de validación de profesion
     * @throws PersonaNotFoundException si la renta no cumple con los criterios de validación de persona
     * @throws RentaFoundException si la renta no cumple con los criterios de validación de renta
     */
    Renta addRenta(Renta renta) throws ProfesionNotFoundException, PersonaNotFoundException, RentaFoundException;

    /**
     * Comprueba si existe una renta en el sistema.
     * @param rentaId id de la renta a comprobar
     * @return true si existe la renta, false en caso contrario
     */

    public boolean existeRenta(int rentaId);
    /**
     * Valida la comprobacion de si existe una renta en el sistema.
     * @param rentaId id de la renta a comprobar
     */
    public void validateRenta(int rentaId) throws RentaFoundException;
}
