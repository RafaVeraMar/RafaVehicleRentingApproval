package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PreApprovalService;
import org.springframework.stereotype.Service;

@Service
public class PreApprovalServiceImpl implements PreApprovalService {

    private ScoringRatingMapper scoringRatingMapper;

    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;

    public PreApprovalServiceImpl(ScoringRatingMapper scoringRatingMapper, InversionIngresosMapper inversionIngresosMapper, PersonaMapper personaMapper, RentaMapper rentaMapper, ImpagosCuotaMapper impagosCuotaMapper) {
        this.scoringRatingMapper = scoringRatingMapper;
        this.inversionIngresosMapper = inversionIngresosMapper;
        this.personaMapper = personaMapper;
        this.rentaMapper = rentaMapper;
        this.impagosCuotaMapper = impagosCuotaMapper;
    }

    @Override
    public Boolean validateInversionIngresos(int solicitudId) {
        if(this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudId)
                <= this.inversionIngresosMapper.obtenerImporteSolicitud(solicitudId)){

            return true;

        }else{
            return false;
        }
    }
    @Override
    public Boolean validateScoringPersona(int solicitudId){
        if(this.scoringRatingMapper.obtenercScoringPersona(solicitudId)
                < 5){

            return true;

        }else{
            return false;
        }
    }

    @Override
    public Boolean validateImpagoCuota(int solicitudId) {
        if(this.impagosCuotaMapper.obtenerImporteImpagoSolicitud(solicitudId)
                <= impagosCuotaMapper.obtenerCuotaSolicitud(solicitudId)){

            return true;

        }else{
            return false;
        }
    }




}

