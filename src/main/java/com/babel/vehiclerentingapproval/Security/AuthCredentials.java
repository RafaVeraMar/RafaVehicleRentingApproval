package com.babel.vehiclerentingapproval.Security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AuthCredentials {
    @Getter @Setter
    private String email;
    private String password;
}
