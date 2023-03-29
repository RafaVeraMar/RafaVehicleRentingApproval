package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.PreApprovalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreApprovalServiceImpl implements PreApprovalService {

    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;

    public PreApprovalServiceImpl(ScoringRatingMapper scoringRatingMapper, EmploymentSeniorityMapper employmentSeniorityMapper, InversionIngresosMapper inversionIngresosMapper, PersonaMapper personaMapper, RentaMapper rentaMapper, SalariedMapper salariedMapper, ImpagosCuotaMapper impagosCuotaMapper) {
        this.scoringRatingMapper = scoringRatingMapper;
        this.employmentSeniorityMapper = employmentSeniorityMapper;
        this.inversionIngresosMapper = inversionIngresosMapper;
        this.personaMapper = personaMapper;
        this.rentaMapper = rentaMapper;
        this.salariedMapper = salariedMapper;
        this.impagosCuotaMapper = impagosCuotaMapper;
    }

    @Override
    public Boolean validateInversionIngresos(int solicitudId) {
        if (this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudId) <= this.inversionIngresosMapper.obtenerImporteSolicitud(solicitudId)) {

            return true;

        } else {
            return false;
        }
    }

    @Override
    public Boolean validateScoringPersona(int solicitudId) {
        if (this.scoringRatingMapper.obtenercScoringPersona(solicitudId) < 5) {

            return true;

        } else {
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


    public Boolean validateCIFCliente(int solicitudId) {
        boolean encontrado = false;
        String cadena;
        String cifSol = this.salariedMapper.obtenerCIFSolicitud(solicitudId);
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
    public Boolean validateInversion(int solicitudId) {
        return this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudId) > 80000;
    }

    @Override
    public Boolean validateNationality(int personaId) {
        String nacionalidad = this.personaMapper.getNationality(personaId);
        boolean espanol = false;

        if (nacionalidad.equalsIgnoreCase("ES")) {
            espanol = true;
        }

        return espanol;
    }


    @Override
    public Boolean validateYearsExperience(int solicitudId) {
        float yearsEmployment = this.employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitudId);
        if (yearsEmployment >= 3) {
            return true;
        } else {
            return false;
        }
    }

}