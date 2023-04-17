package com.babel.vehiclerentingapproval.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * Esta clase define un método para enviar correos electrónicos
 * @author andres.guijarro@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */
@Service
public class EmailServiceImpl {

    private EmailServiceImpl() {
        // Do nothing
    }


    /**
     * Metodo que envía un correo electrónico.
     * @param mensaje el mensaje del correo electrónico
     * @param destino el correo electrónico del destinatario
     * @param asunto el asunto del correo electrónico
     */
    public static void SendMail(String mensaje, String destino, String asunto) {
        //usuario y contraseña del usuario de google que vayamos a utilizar
        String Username = "solicitudrenting@gmail.com";
        //String Password = "hswrinyhboucvsss";
        var clave = new getKey();



        //propiedades del mensaje

        String Mensage = mensaje;
        String To = destino;
        String Subject = asunto;

        //propiedades de la conexión

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, clave.getClave() );
                        //return new PasswordAuthentication(Username, Password );
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            //JOptionPane.showMessageDialog(this, "Su mensaje ha sido enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}

