package com.babel.vehiclerentingapproval;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(version = "1.0.0", description = "Proyecto de Scoring")

)
public class VehicleRentingApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(VehicleRentingApiApplication.class, args);
	}

}
