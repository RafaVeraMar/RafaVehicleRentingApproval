package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.persistance.database.mappers.InversionIngresosMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.RentaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ScoringRatingMapper;
import com.babel.vehiclerentingapproval.services.PreApprovalService;
import org.springframework.stereotype.Service;

@Service
public class PreApprovalServiceImpl implements PreApprovalService {

    private ScoringRatingMapper scoringRatingMapper;

    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;

    public PreApprovalServiceImpl(ScoringRatingMapper scoringRatingMapper, InversionIngresosMapper inversionIngresosMapper, PersonaMapper personaMapper, RentaMapper rentaMapper) {
        this.scoringRatingMapper = scoringRatingMapper;
        this.inversionIngresosMapper = inversionIngresosMapper;
        this.personaMapper = personaMapper;
        this.rentaMapper = rentaMapper;
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


}

