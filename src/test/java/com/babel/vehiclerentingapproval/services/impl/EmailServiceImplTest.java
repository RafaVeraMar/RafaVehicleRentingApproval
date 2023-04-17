package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.services.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

@SpringBootTest
public class EmailServiceImplTest {
    EmailServiceImpl emailService;
    JavaMailSender mailSender;
    @BeforeEach
    void setupAll(){
        this.mailSender= Mockito.mock(JavaMailSender.class);
       emailService = new EmailServiceImpl(mailSender);
    }

    @Test
    public void email_shouldNotThrow_Exception_when_emailIsSent(){
        Assertions.assertDoesNotThrow(()->{
            emailService.sendMail("Mensaje de prueba","andres.guijarro@babelgroup.com","Asunto de ejemplo");
        });
    }

    @Test
    public void email_shouldThrow_MessagingException_when_emailIsWrong(){
        Assertions.assertThrows(MessagingException.class,()->{
            emailService.sendMail("Mensaje de prueba","emailErroneo","Asunto de ejemplo");
        });
    }

}
