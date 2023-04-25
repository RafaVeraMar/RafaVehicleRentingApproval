package com.babel.vehiclerentingapproval;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(version = "1.0.0", description = "Proyecto de Scoring")

)
public class VehicleRentingApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(VehicleRentingApiApplication.class, args);
	}

}
