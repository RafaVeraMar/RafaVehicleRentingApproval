package com.babel.vehiclerentingapproval.services;

import java.io.IOException;
import java.util.Map;


public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}
