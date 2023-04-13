package com.babel.vehiclerentingapproval.services.preautomaticresults.impl;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.AutomaticResultMapper;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.CalculateAutomaticResult;
import com.babel.vehiclerentingapproval.services.preautomaticresults.DenyRulesService;
import org.springframework.stereotype.Service;

/**
 * Clase que contiene los metodos que engloba todos los metodos destinados a comprobar el cumplimiento de reglas de preparobacion,
 * predenegacion, y la obtencion de un estado de la solicitud.
 * @author: ismael.mesa@babelgroup.com
 * @author: alvaro.dorado@babelgroup.com
 */
@Service
public class CalculateAutomaticResultImpl implements CalculateAutomaticResult {

    private DenyRulesService denyRulesService;
    private ApprovalRulesService approvalRulesService;
    private AutomaticResultMapper automaticResultMapper;

    /**
     * Constructor encargado de dar forma al objeto de tipo CalculateAutomaticResult
     * @param denyRulesService parámetro de tipo DenyRulesService que contiene todos las reglas de predenegación
     * @param approvalRulesService parámetro de tipo  ApprovalRulesService que contiene todos las reglas de preaprovación
     * @param automaticResultMapper parámetro de tipo AutomaticResultMapper realiza una query que actualiza el código de resolución
     *                              de una solicitud de renting.
     */
    public CalculateAutomaticResultImpl(DenyRulesService denyRulesService, ApprovalRulesService approvalRulesService, AutomaticResultMapper automaticResultMapper) {
        this.denyRulesService = denyRulesService;
        this.approvalRulesService = approvalRulesService;
        this.automaticResultMapper = automaticResultMapper;
    }

    /**
     * Metodo encargado de comprobar que no se cumpla alguna regla de predenegación
     * @param solicitudRenting la solicitud de renting
     * @return Si no ha encontrado ninguna regla de predenación devuelve True, en caso contrario False.
     */
    @Override
    public Boolean findAnyDeny(SolicitudRenting solicitudRenting) {
        if (!denyRulesService.validateClientAge(solicitudRenting) &&
                !denyRulesService.validateClientAgePlusPlazo(solicitudRenting) &&
                !denyRulesService.validateScoringTitular(solicitudRenting)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de comprobar que todas las reglas de preaprobación de cumplan
     * @param solicitudRenting la solicitud de renting
     * @return Si todas las reglas de preaprobación se cumplen, devuelve True, en caso contrario False.
     */
    @Override
    public Boolean findAllApproval(SolicitudRenting solicitudRenting) {
        //TODO
        // Probar con && validatefindPersonasByCodResultado(solicitudRenting)
        if (approvalRulesService.validateCIFCliente(solicitudRenting) &&
                approvalRulesService.validateInversion(solicitudRenting) &&
                approvalRulesService.validateInversionIngresos(solicitudRenting) &&
                approvalRulesService.validateScoringPersona(solicitudRenting) &&
                approvalRulesService.validateImpagoCuota(solicitudRenting) &&
                approvalRulesService.validateNationality(solicitudRenting) &&
                approvalRulesService.validateYearsExperience(solicitudRenting) &&
                approvalRulesService.validateClienteNoAprobadoConGarantias(solicitudRenting) &&
                approvalRulesService.validateClienteNoRechazadoPreviamente(solicitudRenting)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de comprobar que se cumpla alguna regla de preaprobación
     * @param solicitudRenting solicitud de renting
     * @return Si alguna regla de preaprobación se cumple devuelve True, en caso contrario, devuelve False
     */
    @Override
    public Boolean findAnyApproval(SolicitudRenting solicitudRenting) {
        //TODO
        // Probar con && validatefindPersonasByCodResultado(solicitudRenting)
        if (this.approvalRulesService.validateCIFCliente(solicitudRenting) ||
                this.approvalRulesService.validateInversion(solicitudRenting) ||
                this.approvalRulesService.validateInversionIngresos(solicitudRenting) ||
                this.approvalRulesService.validateScoringPersona(solicitudRenting) ||
                this.approvalRulesService.validateImpagoCuota(solicitudRenting) ||
                this.approvalRulesService.validateNationality(solicitudRenting) ||
                this.approvalRulesService.validateYearsExperience(solicitudRenting) ||
                this.approvalRulesService.validateClienteNoAprobadoConGarantias(solicitudRenting) ||
                this.approvalRulesService.validateClienteNoRechazadoPreviamente(solicitudRenting)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que evalua una solicitud comprobando si:
     * Todas las reglas de preaprobación se cumplen
     * Alguna regla de predenegación se cumple
     * Alguna regla de predenegación se cumple
     * devolviendo el estado de la solicitud, respecto a ello.
     * @param solicitudRenting la solicitud de renting.
     * @return resultado equivalente al estado de la solicitud tras comprobar todas las validaciones.
     * devolviendo: AA = Aprobada, PA = Pendiente Aprobacion, DM = Predenegacion Automatica.
     */
    @Override
    public String totalResult(SolicitudRenting solicitudRenting) {

        String result = "";

        if (this.findAnyDeny(solicitudRenting)) {
            if (this.findAllApproval(solicitudRenting)) {
           //     this.automaticResultMapper.updateCodResolucion(solicitudRenting);
                result = "AA";
            } else if (this.findAnyApproval(solicitudRenting)) {
            //    this.automaticResultMapper.updateCodResolucion(solicitudRenting);
                result = "PA";
            } else {
            //    this.automaticResultMapper.updateCodResolucion(solicitudRenting);
                result = "DM";
            }
        } else {
        //    this.automaticResultMapper.updateCodResolucion(solicitudRenting);
            result = "DM";


        }
        return result;
    }



}
