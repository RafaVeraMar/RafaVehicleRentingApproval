package com.babel.vehiclerentingapproval.services.preautomaticresults;

import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import org.springframework.context.annotation.Bean;


public interface CalculateAutomaticResult {
    @Bean
    Boolean findAnyDeny(SolicitudRenting solicitudRenting);
    @Bean
    Boolean findAllApproval(SolicitudRenting solicitudRenting);
    @Bean
    Boolean findAnyApproval(SolicitudRenting solicitudRenting);
    @Bean
    void totalResult(SolicitudRenting solicitudRenting);

}
