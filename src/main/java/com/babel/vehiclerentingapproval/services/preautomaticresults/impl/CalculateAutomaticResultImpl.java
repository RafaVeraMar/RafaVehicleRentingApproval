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
 *
 * @author: ismael.mesa@babelgroup.com
 * @author: alvaro.dorado@babelgroup.com
 */
@Service
public class CalculateAutomaticResultImpl implements CalculateAutomaticResult {

    private DenyRulesService denyRulesService;
    private ApprovalRulesService approvalRulesService;

    /**
     * Constructor encargado de dar forma al objeto de tipo CalculateAutomaticResult
     *
     * @param denyRulesService      parámetro de tipo DenyRulesService que contiene todos las reglas de predenegación
     * @param approvalRulesService  parámetro de tipo  ApprovalRulesService que contiene todos las reglas de preaprovación
     * @param automaticResultMapper parámetro de tipo AutomaticResultMapper realiza una query que actualiza el código de resolución
     *                              de una solicitud de renting.
     */
    public CalculateAutomaticResultImpl(DenyRulesService denyRulesService, ApprovalRulesService approvalRulesService, AutomaticResultMapper automaticResultMapper) {
        this.denyRulesService = denyRulesService;
        this.approvalRulesService = approvalRulesService;
    }

    /**
     * Metodo encargado de comprobar que no se cumpla alguna regla de predenegación
     *
     * @param solicitudRenting la solicitud de renting
     * @return Si no ha encontrado ninguna regla de predenación devuelve True, en caso contrario False.
     */
    @Override
    public Boolean findAnyDeny(SolicitudRenting solicitudRenting) {
        boolean resultado;
        if (Boolean.FALSE.equals(denyRulesService.validateClientAge(solicitudRenting)) &&
                Boolean.FALSE.equals(denyRulesService.validateClientAgePlusPlazo(solicitudRenting)) &&
                Boolean.FALSE.equals(denyRulesService.validateScoringTitular(solicitudRenting))) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Metodo encargado de comprobar que todas las reglas de preaprobación de cumplan
     *
     * @param solicitudRenting la solicitud de renting
     * @return Si todas las reglas de preaprobación se cumplen, devuelve True, en caso contrario False.
     */
    @Override
    public Boolean findAllApproval(SolicitudRenting solicitudRenting) {
        boolean resultado;
        if (Boolean.TRUE.equals(approvalRulesService.validateCIFCliente(solicitudRenting)) &&
                Boolean.TRUE.equals(approvalRulesService.validateInversion(solicitudRenting)) &&
                        Boolean.TRUE.equals(approvalRulesService.validateInversionIngresos(solicitudRenting)) &&
                                Boolean.TRUE.equals(approvalRulesService.validateScoringPersona(solicitudRenting)) &&
                                        Boolean.TRUE.equals(approvalRulesService.validateImpagoCuota(solicitudRenting)) &&
                                                Boolean.TRUE.equals(approvalRulesService.validateNationality(solicitudRenting)) &&
                                                        Boolean.TRUE.equals(approvalRulesService.validateYearsExperience(solicitudRenting)) &&
                                                                Boolean.TRUE.equals(approvalRulesService.validateClienteNoAprobadoConGarantias(solicitudRenting)) &&
                                                                        Boolean.TRUE.equals(approvalRulesService.validateClienteNoRechazadoPreviamente(solicitudRenting)))
        {
            resultado = true;
        } else{
            resultado = false;
        }
        return resultado;
    }

    /**
     * Metodo encargado de comprobar que se cumpla alguna regla de preaprobación
     *
     * @param solicitudRenting solicitud de renting
     * @return Si alguna regla de preaprobación se cumple devuelve True, en caso contrario, devuelve False
     */
    @Override
    public Boolean findAnyApproval(SolicitudRenting solicitudRenting) {
        boolean resultado;
        if (Boolean.TRUE.equals(this.approvalRulesService.validateCIFCliente(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateInversion(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateInversionIngresos(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateScoringPersona(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateImpagoCuota(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateNationality(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateYearsExperience(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateClienteNoAprobadoConGarantias(solicitudRenting)) ||
                Boolean.TRUE.equals(this.approvalRulesService.validateClienteNoRechazadoPreviamente(solicitudRenting))) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Metodo que evalua una solicitud comprobando si:
     * Todas las reglas de preaprobación se cumplen
     * Alguna regla de predenegación se cumple
     * Alguna regla de predenegación se cumple
     * devolviendo el estado de la solicitud, respecto a ello.
     *
     * @param solicitudRenting la solicitud de renting.
     * @return resultado equivalente al estado de la solicitud tras comprobar todas las validaciones.
     * devolviendo: AA = Aprobada, PA = Pendiente Aprobacion, DM = Predenegacion Automatica.
     */
    @Override
    public String totalResult(SolicitudRenting solicitudRenting) {

        var result = "";

        if (Boolean.TRUE.equals(this.findAnyDeny(solicitudRenting))) {
            if (Boolean.TRUE.equals(this.findAllApproval(solicitudRenting))) {
                result = "AA";
            } else if (Boolean.TRUE.equals(this.findAnyApproval(solicitudRenting))) {
                result = "PA";
            } else {
                result = "DM";
            }
        } else {
            result = "DM";


        }
        return result;
    }


}
