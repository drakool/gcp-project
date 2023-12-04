package com.epam.gcpproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.epam.gcpproject.configuration")
public class GcpProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpProjectApplication.class, args);
	}

}
