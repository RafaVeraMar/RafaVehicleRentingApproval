package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.services.InversionIngresos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/validaciones")
public class InversionIngresosController {

    InversionIngresos inversionIngresos;

    public InversionIngresosController(InversionIngresos inversionIngresos) {
        this.inversionIngresos = inversionIngresos;
    }


}
