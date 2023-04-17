package com.babel.vehiclerentingapproval.services.impl;

import org.springframework.beans.factory.annotation.Value;

public class getKey {
    @Value("${clave}")
    private String clave;

    public String getClave() {
        return clave;
    }
}
