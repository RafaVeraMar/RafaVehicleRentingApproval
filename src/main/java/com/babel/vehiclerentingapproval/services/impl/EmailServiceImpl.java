package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.exceptions.FailedSendingEmail;
import com.babel.vehiclerentingapproval.exceptions.RequestApiValidationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Esta clase define un método para enviar correos electrónicos
 *
 * @author andres.guijarro@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
@Service
public class EmailServiceImpl {

    final JavaMailSender mailSender;

    public EmailServiceImpl (JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Metodo que envía un correo electrónico.
     *
     * @param mensaje el mensaje del correo electrónico
     * @param destino el correo electrónico del destinatario
     * @param asunto  el asunto del correo electrónico
     */
    public static boolean sendMail (String mensaje, String destino, String asunto) throws MessagingException {
        //usuario y contraseña del usuario de google que vayamos a utilizar
        var username = "solicitudrenting@gmail.com";
        var psw = "hswrinyhboucvsss";

        //propiedades del mensaje
        String mensage = mensaje;
        String to = destino;
        String subject = asunto;

        //propiedades de la conexión
        var props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        var session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication ( ) {
                        return new PasswordAuthentication(username, psw);

                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(mensage);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new MessagingException(e.getMessage());
        }

    }


}

