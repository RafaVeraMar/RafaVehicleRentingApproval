package com.babel.vehiclerentingapproval.services.preautomaticresults;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;

public interface ApprovalRulesService {
    Boolean validateInversionIngresos(SolicitudRenting solicitudRenting);

    Boolean validateInversion(SolicitudRenting solicitudRenting);

    Boolean validateScoringPersona(SolicitudRenting solicitudRenting);

    Boolean validateImpagoCuota(SolicitudRenting solicitudRenting);

    Boolean validateCIFCliente(SolicitudRenting solicitudRenting);

    Boolean validateNationality(SolicitudRenting solicitudRenting);

    Boolean validateYearsExperience(SolicitudRenting solicitudRenting);

    /**
     * Cabecera del método PreAprobación que comprueba si un cliente existe y es garante
     *
     * @param solicitudRenting
     * @return False si el cliente no existe o no es garante y True el cliente existe y es garante
     */

    Boolean validatefindPersonasByCodResultado(SolicitudRenting solicitudRenting);

    /**
     * Cabecera del método PreAprobación que comprueba si un cliente no se aprueba con garantías
     *
     * @param solicitudRenting
     * @return False si la persona no se ha aprobado y True si sí se ha encontrado
     */
    Boolean validateClienteNoAprobadoConGarantias(SolicitudRenting solicitudRenting);

    /**
     * Cabecera del método PreAprobación que comprueba si un cliente no se ha rechazado previamente
     *
     * @param solicitudRenting
     * @return False si la persona no se ha aprobado y True si sí se ha encontrado
     */
    Boolean validateClienteNoRechazadoPreviamente(SolicitudRenting solicitudRenting);

}

