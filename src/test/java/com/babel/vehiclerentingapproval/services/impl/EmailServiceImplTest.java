package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.EstadoSolicitudNotFoundException;
import com.babel.vehiclerentingapproval.exceptions.FailedSendingEmail;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import com.babel.vehiclerentingapproval.models.TipoResultadoSolicitud;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.SolicitudRentingMapper;
import com.babel.vehiclerentingapproval.persistance.database.mappers.TipoResultadoSolicitudMapper;
import com.babel.vehiclerentingapproval.services.CodigoResolucionValidator;
import com.babel.vehiclerentingapproval.services.EmailService;
import com.babel.vehiclerentingapproval.services.PersonaService;
import com.babel.vehiclerentingapproval.services.SolicitudRentingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class EmailServiceImplTest {
    EmailService emailService;
    JavaMailSender mailSender;

    SolicitudRentingService solicitudService;
    TipoResultadoSolicitudMapper tipoResultadoSolicitudMapper;
    SolicitudRentingMapper solicitudRentingMapper;
    CodigoResolucionValidator codigoResolucionValidator;
    PersonaService personaService;
    PersonaMapper personaMapper;

    @BeforeEach
    void setupAll(){
        mailSender= Mockito.mock(JavaMailSender.class);
        emailService = Mockito.mock(EmailService.class);

        tipoResultadoSolicitudMapper = Mockito.mock(TipoResultadoSolicitudMapper.class);
        solicitudRentingMapper = Mockito.mock(SolicitudRentingMapper.class);
        personaService = Mockito.mock(PersonaService.class);
        codigoResolucionValidator = new CodigoResolucionValidatorImpl(tipoResultadoSolicitudMapper);
        solicitudService = new SolicitudRentingServiceImpl(solicitudRentingMapper, tipoResultadoSolicitudMapper, personaService, codigoResolucionValidator, personaMapper,emailService);

    }
    /*
    @Test
    void email_shouldNotThrow_Exception_when_emailIsSent() throws MessagingException {
        String dest = "blabla@gmail.com";
        String message = "Buenas";
        String asunto = "Importante";



        //Assertions.assertDoesNotThrow(() -> {
       //     EmailServiceImpl.sendMail(message,dest,asunto);
        //});

        //Assertions.assertTrue(EmailServiceImpl.sendMail(message,dest,asunto));
    }
    private List<String> creaListaMock() {
        List<String> listaMock = new ArrayList<>();
        listaMock.add("AA");
        return listaMock;
    }
    private boolean devuelveTrue(String email){
        return true;
    }

    @Test
    void email_shouldThrow_MessagingException_when_emailIsWrong() throws MessagingException, RequestApiValidationException {
        String dest = "blabla@gmail.com";
        String message = "Buenas";
        String asunto = "Importante";
    }
*/
}
