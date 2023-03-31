package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/validaciones")
public class InversionIngresosController {

    private ApprovalRulesService approvalService;

    public InversionIngresosController(ApprovalRulesService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping("")
    ResponseEntity makeProofs(@RequestParam SolicitudRenting solicitudRentingl){
        boolean t = this.approvalService.validateInversionIngresos(solicitudRentingl);
        boolean t1 = this.approvalService.validateScoringPersona(solicitudRentingl);
        boolean t2 = this.approvalService.validateCIFCliente(solicitudRentingl);
        boolean t3 = this.approvalService.validateYearsExperience(solicitudRentingl);
        boolean t4 = this.approvalService.validateInversion(solicitudRentingl);
        boolean t5= this.approvalService.validateImpagoCuota(solicitudRentingl);
        boolean t6 = this.approvalService.validateNationality(solicitudRentingl);
        ArrayList<Boolean> lista =  new ArrayList<Boolean>();


        lista.add(t);
        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
        lista.add(t4);
        lista.add(t5);
        lista.add(t6);

        return ResponseEntity.ok((lista));
    }

}
