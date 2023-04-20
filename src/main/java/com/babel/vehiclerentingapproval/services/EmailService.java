package com.babel.vehiclerentingapproval.services;


/**
 * Esta interfaz define un método para enviar correos electrónicos
 *
 * @author andres.guijarro@babelgroup.com
 * @author enrique.munoz@babelgroup.com
 */

public interface EmailService {
    /**
     * Metodo que envía un correo electrónico.
     *
     * @param mensaje el mensaje del correo electrónico
     * @param destino el correo electrónico del destinatario
     * @param asunto  el asunto del correo electrónico
     */
    boolean sendMail (String mensaje, String destino, String asunto);
}
