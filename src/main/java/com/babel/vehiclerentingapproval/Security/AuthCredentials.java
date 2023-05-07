package com.babel.vehiclerentingapproval.Security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class AuthCredentials {

    private String email;

    private String password;
}
