package com.babel.vehiclerentingapproval.services;

import java.io.IOException;
import java.util.Map;


public interface EmailService {
    public void SendMail(String mensaje,String destino, String asunto);
}
