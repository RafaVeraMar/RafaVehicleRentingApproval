package com.babel.vehiclerentingapproval.services.preautomaticresults.impl;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalRulesServiceImpl implements ApprovalRulesService {

    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;
    private static final int inversionMayor = 80000;
    private static final int scoringRating = 5;

    public ApprovalRulesServiceImpl(ScoringRatingMapper scoringRatingMapper, EmploymentSeniorityMapper employmentSeniorityMapper, InversionIngresosMapper inversionIngresosMapper, PersonaMapper personaMapper, RentaMapper rentaMapper, SalariedMapper salariedMapper, ImpagosCuotaMapper impagosCuotaMapper) {
        this.scoringRatingMapper = scoringRatingMapper;
        this.employmentSeniorityMapper = employmentSeniorityMapper;
        this.inversionIngresosMapper = inversionIngresosMapper;
        this.personaMapper = personaMapper;
        this.rentaMapper = rentaMapper;
        this.salariedMapper = salariedMapper;
        this.impagosCuotaMapper = impagosCuotaMapper;
    }

    @Override
    public Boolean validateInversionIngresos(SolicitudRenting solicitudRenting) {
        if (this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudRenting) <= this.inversionIngresosMapper.obtenerImporteSolicitud(solicitudRenting)) {

            return true;

        } else {
            return false;
        }
    }
    @Override
    public Boolean validateInversion(SolicitudRenting solicitudRenting) {
        return this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudRenting) > inversionMayor;
    }

    @Override
    public Boolean validateScoringPersona(SolicitudRenting solicitudRenting) {
        float valorScoring = this.scoringRatingMapper.obtenercScoringPersona(solicitudRenting);
        if ( valorScoring< scoringRating) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Boolean validateImpagoCuota(SolicitudRenting solicitudRenting) {
        if(this.impagosCuotaMapper.obtenerImporteImpagoSolicitud(solicitudRenting)
                <= impagosCuotaMapper.obtenerCuotaSolicitud(solicitudRenting)){

            return true;

        }else{
            return false;
        }
    }
    @Override
    public Boolean validateCIFCliente(SolicitudRenting solicitudRenting) {
        boolean encontrado = false;
        String cadena;
        String cifSol = this.salariedMapper.obtenerCIFSolicitud(solicitudRenting);
        List<String> listaCIF = this.salariedMapper.obtenerCIFInforma();
        if (!cifSol.isEmpty() && cifSol != null) {
            for (String cif : listaCIF) {
                cadena = cif.trim();
                if (cadena.equals(cifSol)) {
                    encontrado = true;
                }
            }
            return encontrado;
        } else {
            return false;
        }
    }

    @Override
    public Boolean validateNationality(SolicitudRenting solicitudRenting) {
        String nacionalidad = solicitudRenting.getPersona().getNacionalidad();
        boolean espanol = false;

        if (nacionalidad != null && nacionalidad.equalsIgnoreCase("ES")) {
            espanol = true;
        }

        return espanol;
    }


    @Override
    public Boolean validateYearsExperience(SolicitudRenting solicitudRenting) {
        float yearsEmployment = this.employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitudRenting);
        if (yearsEmployment >= 3) {
            return true;
        } else {
            return false;
        }
    }

}
