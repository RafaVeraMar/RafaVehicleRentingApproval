package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.services.PreApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/validaciones")
public class InversionIngresosController {

    private PreApprovalService approvalService;

    public InversionIngresosController(PreApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping("")
    ResponseEntity makeProofs(@RequestParam int solicitudId){
        boolean t = this.approvalService.validateInversionIngresos(solicitudId);
        boolean t1 = this.approvalService.validateScoringPersona(solicitudId);
        boolean t2= this.approvalService.validateImpagoCuota(solicitudId);
        ArrayList<Boolean> lista =  new ArrayList<Boolean>();

        lista.add(t);
        lista.add(t1);
        lista.add(t2);
        return ResponseEntity.ok((lista));
    }

}
