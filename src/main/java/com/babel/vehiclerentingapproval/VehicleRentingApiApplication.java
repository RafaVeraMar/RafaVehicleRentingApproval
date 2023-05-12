package com.babel.vehiclerentingapproval;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEncryptableProperties
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(version = "1.0.0", description = "Proyecto de Scoring")

)

public class VehicleRentingApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleRentingApiApplication.class, args);}

}
